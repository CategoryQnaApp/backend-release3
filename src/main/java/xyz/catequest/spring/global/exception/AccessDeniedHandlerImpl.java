package xyz.catequest.spring.global.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import xyz.catequest.spring.global.dto.CustomException;
import xyz.catequest.spring.global.dto.Response;
import xyz.catequest.spring.global.enums.ErrorMessage;

import java.io.IOException;

@Component
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.error("Access Denied: {}", ErrorMessage.FORBIDDEN_USER.getMessage());

		CustomException ex = new CustomException(ErrorMessage.FORBIDDEN_USER);
		ObjectMapper objectMapper = new ObjectMapper();
		String errorJson = objectMapper.writeValueAsString(Response.fail(ErrorMessage.FORBIDDEN_USER.getStatus(), ex));

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.getWriter().write(errorJson);
	}
}
