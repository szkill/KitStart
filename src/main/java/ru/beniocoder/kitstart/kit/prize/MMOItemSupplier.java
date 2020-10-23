package ru.beniocoder.kitstart.kit.prize;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.Indyuce.mmoitems.MMOItems;

public class MMOItemSupplier extends AbstractItemSupplier {

    @JsonCreator
    public MMOItemSupplier( //@JsonProperty("type") String type,
                            @JsonProperty("type") String type,
                            @JsonProperty("id") String id,
                            @JsonProperty("amount") int amount) {
        super(MMOItems.plugin.getItems().getMMOItem(MMOItems.plugin.getTypes().get(type), id).newBuilder().build());
        // super(MMOItems.getItems().getItem(type, id));
        if (amount >= 1) {
            itemStack.setAmount(amount);

        }
    }

}