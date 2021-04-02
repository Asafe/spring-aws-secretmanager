package io.github.sdacode.exception;

import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

public class SecretsManagerPropertyNotFoundException extends RuntimeException {

    public SecretsManagerPropertyNotFoundException(String property, SecretsManagerException e) {
        super("Secrets Manager can't find the specified secret: ".concat(property), e);
    }

}
