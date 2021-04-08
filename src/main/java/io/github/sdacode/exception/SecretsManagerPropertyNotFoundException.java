package io.github.sdacode.exception;

import com.amazonaws.services.secretsmanager.model.AWSSecretsManagerException;

public class SecretsManagerPropertyNotFoundException extends RuntimeException {

    public SecretsManagerPropertyNotFoundException(String property, AWSSecretsManagerException e) {
        super("Secrets Manager can't find the specified secret: ".concat(property), e);
    }

}
