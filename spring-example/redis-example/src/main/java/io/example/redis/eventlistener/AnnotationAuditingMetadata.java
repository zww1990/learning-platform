package io.example.redis.eventlistener;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.util.Optionals;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.data.util.ReflectionUtils.AnnotationFieldFilter;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Annotation Auditing Metadata
 * @author zww
 * @since 2023-08-11 14:25:00
 */
public class AnnotationAuditingMetadata {

    private static final AnnotationFieldFilter CREATED_DATE_FILTER = new AnnotationFieldFilter(CreatedDate.class);
    private static final AnnotationFieldFilter LAST_MODIFIED_DATE_FILTER = new AnnotationFieldFilter(LastModifiedDate.class);

    private static final Map<Class<?>, AnnotationAuditingMetadata> metadataCache = new ConcurrentHashMap<>();

    static final List<String> SUPPORTED_DATE_TYPES;

    static {

        SUPPORTED_DATE_TYPES = List.of(Date.class.getName(), Long.class.getName(), long.class.getName());
    }

    private final Optional<Field> createdDateField;
    private final Optional<Field> lastModifiedDateField;

    /**
     * Creates a new {@link AnnotationAuditingMetadata} instance for the given type.
     *
     * @param type must not be {@literal null}.
     */
    private AnnotationAuditingMetadata(Class<?> type) {

        Assert.notNull(type, "Given type must not be null");

        this.createdDateField = Optional.ofNullable(ReflectionUtils.findField(type, CREATED_DATE_FILTER));
        this.lastModifiedDateField = Optional.ofNullable(ReflectionUtils.findField(type, LAST_MODIFIED_DATE_FILTER));

        assertValidDateFieldType(createdDateField);
        assertValidDateFieldType(lastModifiedDateField);
    }

    /**
     * Checks whether the given field has a type that is a supported date type.
     *
     * @param field 字段
     */
    private void assertValidDateFieldType(Optional<Field> field) {

        field.ifPresent(it -> {

            if (SUPPORTED_DATE_TYPES.contains(it.getType().getName())) {
                return;
            }

            Class<?> type = it.getType();

            if (Jsr310Converters.supports(type)) {
                return;
            }

            throw new IllegalStateException(String.format(
                    "Found created/modified date field with type %s but only %s as well as java.time types are supported", type,
                    SUPPORTED_DATE_TYPES));
        });
    }

    /**
     * Return a {@link AnnotationAuditingMetadata} for the given {@link Class}.
     *
     * @param type the type to inspect, must not be {@literal null}.
     */
    public static AnnotationAuditingMetadata getMetadata(Class<?> type) {
        return metadataCache.computeIfAbsent(type, AnnotationAuditingMetadata::new);
    }

    /**
     * Returns whether the {@link Class} represented in this instance is auditable or not.
     */
    public boolean isAuditable() {
        return Optionals.isAnyPresent(createdDateField, lastModifiedDateField);
    }

    /**
     * Return the field annotated by {@link CreatedDate}.
     */
    public Optional<Field> getCreatedDateField() {
        return createdDateField;
    }

    /**
     * Return the field annotated by {@link LastModifiedDate}.
     */
    public Optional<Field> getLastModifiedDateField() {
        return lastModifiedDateField;
    }
}
