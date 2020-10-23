package ru.beniocoder.kitstart.kit.prize;

import com.fasterxml.jackson.annotation.JsonCreator;
import ru.abstractcoder.benioapi.item.ItemData;

public class DefaultItemSupplier extends AbstractItemSupplier {

    @JsonCreator
    public DefaultItemSupplier(ItemData itemData) {
        super(itemData.toItemStack());
    }

}