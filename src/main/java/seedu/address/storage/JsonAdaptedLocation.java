package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.exceptions.InvalidIndexException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.location.Location;
import seedu.address.model.attribute.Address;
import seedu.address.model.attribute.Name;

/**
 * Jackson-friendly version of {@link Location}.
 */
public class JsonAdaptedLocation {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Location's %s field is missing!";

    private final String name;
    private final String address;
    private final String id;

    /**
     * Constructs a {@code JsonAdaptedLocation} with the given location details.
     */
    @JsonCreator
    public JsonAdaptedLocation(@JsonProperty("name") String name, @JsonProperty("address") String address,
                               @JsonProperty("id") String id) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    /**
     * Converts a given {@code Location} into this class for Jackson use.
     */
    public JsonAdaptedLocation(Location source) {
        name = source.getName().fullName;
        address = source.getAddress().value;
        id = source.getId().toString();
    }

    /**
     * Converts this Jackson-friendly adapted location object into the model's {@code Location} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted location.
     */
    public Location toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (id == null) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }
        final Index modelId;
        try {
            modelId = Index.fromOneBased(Integer.parseInt(id));
        } catch (ClassCastException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }

        return new Location(modelName, modelAddress, modelId);
    }
}
