package com.github.danilogmoura.algafood.api.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.github.danilogmoura.algafood.domain.exception.EntidadeEmUsoException;
import com.github.danilogmoura.algafood.domain.exception.EntidadeNaoEcontradaException;
import com.github.danilogmoura.algafood.domain.exception.NegocioException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    public static final String MSG_ERRO_GENERICO_USUARIO_FINAL = "Ocorreu um errro interno inesperado no sistema. "
        + "Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerException(Exception ex, WebRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var problemType = ProblemType.ERRO_DE_SISTEMA;
        var detail = MSG_ERRO_GENERICO_USUARIO_FINAL;

        // Importante colocar o printStackTrace (pelo menos por enquanto, que não estamos
        // fazendo logging) para mostrar a stacktrace no console
        // Se não fizer isso, você não vai ver a stacktrace de exceptions que seriam importantes
        // para você durante, especialmente na fase de desenvolvimento
        ex.printStackTrace();

        var problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
        HttpStatus status, WebRequest request) {

        if (ex instanceof MethodArgumentTypeMismatchException exception) {
            return handleMethodArgumentTypeMismatchException(exception, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {

        var probemType = ProblemType.PARAMETRO_INVALIDO;
        var detail = String.format(
            "O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe um valor "
                + "compatível com o tipo %s.", ex.getName(), ex.getValue(),
            ex.getParameter().getParameterType().getSimpleName());

        var problem = createProblemBuilder(status, probemType, detail)
            .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {

        var rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException exception) {
            return handleInvalidFormatException(exception, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException exception) {
            return handlePropertyBindingException(exception, headers, status, request);
        }

        var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        var detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

        var problem = createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers,
        HttpStatus status, WebRequest request) {

        var path = joinPath(ex.getPath());
        var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        var detail = String.format(
            "A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente.", path);

        var problem = createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
        HttpStatus status, WebRequest request) {

        var path = joinPath(ex.getPath());
        var value = ex.getValue();
        var type = ex.getTargetType().getSimpleName();

        var problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        var detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido."
            + " Corrija e informe um valor compatível com o tipo %s", path, value, type);

        var problem = createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
        HttpStatus status, WebRequest request) {

        var problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        var detail = String.format("O recurso '%s' que você tentou acessar, é inexistente.", ex.getRequestURL());

        var problem = createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(EntidadeNaoEcontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEcontrada(EntidadeNaoEcontradaException ex,
        WebRequest request) {

        var status = HttpStatus.NOT_FOUND;
        var problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        var detail = ex.getMessage();

        var problem = createProblemBuilder(status, problemType, detail)
            .userMessage(detail)
            .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<Object> handleEntidadeEmUso(EntidadeEmUsoException ex, WebRequest request) {

        var status = HttpStatus.CONFLICT;
        var problemType = ProblemType.ENTIDADE_EM_USO;
        var detail = ex.getMessage();

        var problem = createProblemBuilder(status, problemType, detail)
            .userMessage(detail)
            .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {

        var status = HttpStatus.BAD_REQUEST;
        var problemType = ProblemType.ERRO_NEGOCIO;
        var detail = ex.getMessage();

        var problem = createProblemBuilder(status, problemType, detail)
            .userMessage(detail)
            .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
        HttpStatus status, WebRequest request) {

        if (body == null) {
            body = Problem.builder()
                .title(status.getReasonPhrase())
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
                .build();
        } else if (body instanceof String title) {
            body = Problem.builder()
                .title(title)
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
                .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
        return Problem.builder()
            .status(status.value())
            .title(problemType.getTitle())
            .type(problemType.getUri())
            .timestamp(LocalDateTime.now())
            .detail(detail);
    }

    private String joinPath(List<Reference> references) {
        return references.stream()
            .map(Reference::getFieldName)
            .collect(Collectors.joining("."));
    }
}
