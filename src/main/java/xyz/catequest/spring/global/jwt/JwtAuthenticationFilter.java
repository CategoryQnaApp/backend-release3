package xyz.catequest.spring.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.catequest.spring.domain.auth.service.AuthService;
import xyz.catequest.spring.domain.user.enums.UserRole;
import xyz.catequest.spring.global.dto.AccessTokenResponse;
import xyz.catequest.spring.global.dto.CustomException;
import xyz.catequest.spring.global.dto.Response;
import xyz.catequest.spring.global.entity.AuthUser;
import xyz.catequest.spring.global.enums.ErrorMessage;
import xyz.catequest.spring.global.enums.RedirectionMessage;
import xyz.catequest.spring.global.utils.JwtProvider;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final String APP_JSON = "application/json";
  private static final String UTF = "UTF-8";
  private static final String AUTHORIZATION_HEADER = "Authorization";

  private final JwtProvider jwtProvider;
  private final AuthService authService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    String authHeader = request.getHeader(AUTHORIZATION_HEADER);

    try {
      if (authHeader != null && authHeader.startsWith("Bearer ")) {
        String token = jwtProvider.removeBearerPrefix(authHeader);
        Claims claims = jwtProvider.extractClaims(token);
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
          setAuthentication(claims);
        }
      }

    } catch (SecurityException | MalformedJwtException e) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      sendError(response, ErrorMessage.INVALID_JWT_SIGNATURE);
      return;
    } catch (ExpiredJwtException e) {
      handleExpiredToken(request, response);
      return;
    } catch (UnsupportedJwtException e) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      sendError(response, ErrorMessage.UNSUPPORTED_JWT_TOKEN);
      return;
    }
    filterChain.doFilter(request, response);
  }

  private void setAuthentication(Claims claims) {
    Long userId = Long.valueOf(claims.getSubject());
    UserRole userRole = UserRole.of(claims.get("role", String.class));

    AuthUser authUser = AuthUser.of(userId, userRole);
    JwtAuthenticationToken authenticationToken = JwtAuthenticationToken.of(authUser);
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
  }

  private void sendError(HttpServletResponse response, ErrorMessage error) throws IOException {
    log.error(error.name() + " : ", error.getMessage());

    CustomException ex = new CustomException(error);
    ObjectMapper objectMapper = new ObjectMapper();
    String errorJson = objectMapper.writeValueAsString(Response.fail(error.getStatus(), ex));

    response.setContentType(APP_JSON);
    response.setCharacterEncoding(UTF);
    response.getWriter().write(errorJson);
  }

  private void sendRedirect(HttpServletResponse response, RedirectionMessage message)
      throws IOException {
    log.info(message.name() + " : ", message.getMessage());

    ObjectMapper objectMapper = new ObjectMapper();
    String redirect = objectMapper.writeValueAsString(Response.success(message.getStatus()));

    response.setContentType(APP_JSON);
    response.setCharacterEncoding(UTF);
    response.getWriter().write(redirect);
  }

  private void handleExpiredToken(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    log.info("accessToken 재발급");
    String refreshToken = request.getHeader("refreshToken");

    if (refreshToken == null) {
      response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
      sendRedirect(response, RedirectionMessage.EXPIRED_JWT_ACCESS_TOKEN);
      return;
    }

    String accessToken = authService.refreshAccessToken(refreshToken);

    ObjectMapper objectMapper = new ObjectMapper();
    String returnToken =
        objectMapper.writeValueAsString(Response.success(AccessTokenResponse.of(accessToken)));

    response.setContentType(APP_JSON);
    response.setCharacterEncoding(UTF);
    response.getWriter().write(returnToken);
  }
}
