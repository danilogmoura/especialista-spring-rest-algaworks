package com.github.danilogmoura.algafood.core.validation;

import java.math.BigDecimal;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import org.springframework.beans.BeanUtils;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {

    private String valorField;
    private String descricaoFiel;
    private String descricaoObrigatoria;

    @Override
    public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
        this.valorField = constraintAnnotation.valorField();
        this.descricaoFiel = constraintAnnotation.descricaoFiel();
        this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();
    }

    @Override
    public boolean isValid(Object objectClass, ConstraintValidatorContext context) {
        try {
            boolean valido = true;

            var valor = (BigDecimal) BeanUtils.getPropertyDescriptor(objectClass.getClass(), valorField)
                .getReadMethod().invoke(objectClass);

            var descricao = (String) BeanUtils.getPropertyDescriptor(objectClass.getClass(), descricaoFiel)
                .getReadMethod().invoke(objectClass);

            if (valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
                valido = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
            }

            return valido;
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }
}
