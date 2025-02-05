package arguments.providers;

import arguments.holders.AuthValidationArgumentsHolder;
import consts.UrlParamValues;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public class AuthValidationArgumentsProvider implements ArgumentsProvider {


    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new AuthValidationArgumentsHolder(
                    Collections.emptyMap()
                ),
                new AuthValidationArgumentsHolder(
                Map.of("key", UrlParamValues.VALID_KEY)
                )
                /*,
                new AuthValidationArgumentsHolder(
                Map.of("token",UrlParamValues.VALID_TOKEN) //this will cause false negative in the tests due to it showing an "invalid key" message
                 )
                        */
        ).map(Arguments::of);
    }
}
