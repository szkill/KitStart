package ru.beniocoder.kitstart.kit.prize;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "kind",
        defaultImpl = DefaultItemSupplier.class
)
@JsonSubTypes({
        @Type(name = "MMO", value = MMOItemSupplier.class)
})
public interface ItemSupplier extends Supplier<ItemStack> {


}