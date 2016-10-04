package engineers.core.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBase extends Item {
	public ItemBase(String name) {
		setRegistryName(name);
		setUnlocalizedName(name);
		GameRegistry.register(this);
	}
}
