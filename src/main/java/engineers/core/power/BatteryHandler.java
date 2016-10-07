package engineers.core.power;

import engineers.core.items.batteries.ItemBattery;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class BatteryHandler {

	public static long takePower(ItemStack stack, long power, boolean simulated) {
		return 0;
	}

	public static long getStoredPower(ItemStack stack) {
		if (isBattery(stack) && stack.getTagCompound() != null && stack.getTagCompound().hasKey("power"))
			return stack.getTagCompound().getLong("power");
		return 0;
	}

	public static long getCapacity(ItemStack stack) {
		return ItemBattery.getType(stack).getMax();
	}

	public static long getUnfilled(ItemStack stack) {
		return getCapacity(stack) - getStoredPower(stack);
	}

	public static ItemStack givePower(ItemStack stack, long power) {
		return setPower(stack, getStoredPower(stack) + power);
	}

	public static boolean isBattery(ItemStack stack) {
		return stack.getItem() instanceof ItemBattery;
	}

	public static ItemBattery getItemBattery(ItemStack stack) {
		if (isBattery(stack))
			return (ItemBattery) stack.getItem();
		return null;
	}

	public static ItemStack setPower(ItemStack stack, long power) {
		if (stack.getTagCompound() == null)
			stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setLong("power", power);
		return stack;
	}

}
