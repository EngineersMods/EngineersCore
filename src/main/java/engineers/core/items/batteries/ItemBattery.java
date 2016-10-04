package engineers.core.items.batteries;

import java.util.List;

import engineers.core.items.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemBattery extends ItemBase {
	public static enum BatteryTypes {
		BASIC(4000, "Low"), ADVANCED(4000, "Advanced"), FUTURISTIC(4000, "Futuristic");
		int max;
		String name;

		BatteryTypes(int max, String name) {
			this.max = max;
			this.name = name;
		}

		public int getMax() {
			return max;
		}

		public String getName() {
			return name;
		}
	}

	public ItemBattery(BatteryTypes type) {
		super("battery" + type.name);
	}

	public static BatteryTypes getType(ItemStack stack) {
		for (BatteryTypes type : BatteryTypes.values()) {
			if (stack.getItem().getRegistryName().toString().endsWith(type.name))
				return type;
		}
		return null;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add("Type: " + getType(stack).name);
		tooltip.add("1337/" + getType(stack).max);
		super.addInformation(stack, playerIn, tooltip, advanced);
	}

}
