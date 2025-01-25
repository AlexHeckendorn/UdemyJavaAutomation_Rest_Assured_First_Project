package arguments.providers;

import arguments.holders.CardIDValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class CardIdValidationArgumentsProvider implements ArgumentsProvider  {

    @Override
    public Stream provideArguments(ExtensionContext context)  {
        return Stream.of(
                new CardIDValidationArgumentsHolder(
                        Map.of("id", "674c92e5eced638073dd548f"),
                        "The requested resource was not found.",
                        404
                ),
                new CardIDValidationArgumentsHolder(
                        Map.of("id", "invalid"),
                        "invalid id",
                        400
                )
        ).map(Arguments::of);
    }
}
