package arguments.providers;

import arguments.holders.BoardIDValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class BoardIdValidationArgumentsProvider implements ArgumentsProvider  {

    @Override
    public Stream provideArguments(ExtensionContext context)  {
        return Stream.of(
                new BoardIDValidationArgumentsHolder(
                        Map.of("id", "invalid"),
                        "invalid id",
                        400
                ),
                new BoardIDValidationArgumentsHolder(
                        Map.of("id", "674a47bffbdd3bfa5b675973"), //need to ensure that this ID is an invalid board ID
                        "The requested resource was not found.",
                        404
                )
        ).map(Arguments::of);
    }
}
