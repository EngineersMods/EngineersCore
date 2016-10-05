package engineers.core.items.batteries;

import java.util.List;

import engineers.core.items.ItemBase;
import engineers.core.power.BatteryHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBattery extends ItemBase {
	public static enum BatteryTypes {
		BASIC(4000, "Low"), ADVANCED(16000, "Advanced"), FUTURISTIC(64000, "Futuristic");
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
		tooltip.add(BatteryHandler.getStoredPower(stack) + "/" + BatteryHandler.getCapacity(stack));
		super.addInformation(stack, playerIn, tooltip, advanced);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		player.setHeldItem(hand, BatteryHandler.givePower(stack, 100L));
		return super.onItemRightClick(stack, world, player, hand);
	}

}
