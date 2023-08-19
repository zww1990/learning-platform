package io.example.graphql;

import graphql.introspection.Introspection;
import graphql.language.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.graphql.execution.GraphQlSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Pagination Type Definition Registry
 *
 * @author zww
 * @since 2023-08-19 20:52:10
 */
@SuppressWarnings("rawtypes")
@Slf4j
public class PaginationTypeDefinitionRegistry implements GraphQlSourceBuilderCustomizer {
    @Override
    public void customize(GraphQlSource.SchemaResourceBuilder builder) {
        builder.configureTypeDefinitions(schemaRegistry -> {
            log.debug("1-> Directive Definitions Size = {}", schemaRegistry.getDirectiveDefinitions().size());
            List<SDLDefinition> tmp = new ArrayList<>();
            if (!schemaRegistry.getDirectiveDefinitions().containsKey("connection")) {
                DirectiveDefinition directive = DirectiveDefinition.newDirectiveDefinition()
                        .name("connection")
                        .description(this.createDescription("Connection"))
                        .directiveLocation(DirectiveLocation.newDirectiveLocation()
                                .name(Introspection.DirectiveLocation.OBJECT.name())
                                .build())
                        .build();
                tmp.add(directive);
            }
            Map<String, TypeDefinition> definitions = schemaRegistry.types();
            List<SDLDefinition> connectionTypes = this.parseConnectionDirective(definitions.values());
            if (!connectionTypes.isEmpty()) {
                schemaRegistry.addAll(connectionTypes);
                log.debug("2-> Directive Definitions Size = {}", schemaRegistry.getDirectiveDefinitions().size());
            }
            if (!tmp.isEmpty()) {
                schemaRegistry.addAll(tmp);
                log.debug("3-> Directive Definitions Size = {}", schemaRegistry.getDirectiveDefinitions().size());
            }
            log.debug("4-> Directive Definitions Size = {}", schemaRegistry.getDirectiveDefinitions().size());
        });
    }

    private List<SDLDefinition> parseConnectionDirective(Collection<TypeDefinition> types) {
        List<SDLDefinition> definitions = new ArrayList<>();
        types.stream()
                .filter(it -> it instanceof ObjectTypeDefinition
                        || it instanceof InterfaceTypeDefinition
                        || it instanceof UnionTypeDefinition)
                .filter(it -> it.hasDirective("connection"))
                .forEach(it -> {
                    definitions.add(this.createConnection(it.getName()));
                    definitions.add(createEdge(it.getName()));
                });
        if (types.stream().anyMatch(it -> it.hasDirective("connection"))
                && types.stream().noneMatch(it -> it.getName().equals("PageInfo"))) {
            definitions.add(this.createPageInfo());
        }
        return definitions;
    }

    private TypeDefinition createPageInfo() {
        return ObjectTypeDefinition.newObjectTypeDefinition()
                .name("PageInfo")
                .description(this.createDescription("PageInfo"))
                .fieldDefinition(this.createFieldDefinition("hasPreviousPage", new NonNullType(new TypeName("Boolean"))))
                .fieldDefinition(this.createFieldDefinition("hasNextPage", new NonNullType(new TypeName("Boolean"))))
                .fieldDefinition(this.createFieldDefinition("startCursor", new TypeName("String")))
                .fieldDefinition(this.createFieldDefinition("endCursor", new TypeName("String")))
                .build();
    }

    private TypeDefinition createEdge(String type) {
        return ObjectTypeDefinition.newObjectTypeDefinition()
                .name(type + "Edge")
                .description(this.createDescription("$type Edge"))
                .fieldDefinition(this.createFieldDefinition("cursor", new TypeName("String")))
                .fieldDefinition(this.createFieldDefinition("node", new TypeName(type)))
                .build();
    }

    private TypeDefinition createConnection(String type) {
        return ObjectTypeDefinition.newObjectTypeDefinition()
                .name(type + "Connection")
                .description(this.createDescription("$type Connection"))
                .fieldDefinition(this.createFieldDefinition("edges", new ListType(new TypeName(type + "Edge"))))
                .fieldDefinition(this.createFieldDefinition("pageInfo", new NonNullType(new TypeName("PageInfo"))))
                .build();
    }

    private FieldDefinition createFieldDefinition(String name, Type type) {
        return new FieldDefinition(name, type).transform(it -> it.description(this.createDescription("Field $name")));
    }

    private Description createDescription(String content) {
        return new Description(content, SourceLocation.EMPTY, false);
    }
}
