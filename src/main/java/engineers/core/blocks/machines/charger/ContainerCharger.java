package engineers.core.blocks.machines.charger;

import engineers.core.items.batteries.ItemBattery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerCharger extends Container {

	// Stores the tile entity instance for later use
	private TileCharger tileInventoryFurnace;

	// These store cache values, used by the server to only update the client
	// side tile entity when values have changed
	private int[] cachedFields;

	// must assign a slot index to each of the slots used by the GUI.
	// For this container, we can see the furnace fuel, input, and output slots
	// as well as the player inventory slots and the hotbar.
	// Each time we add a Slot to the container using addSlotToContainer(), it
	// automatically increases the slotIndex, which means
	// 0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers
	// 0 - 8)
	// 9 - 35 = player inventory slots (which map to the InventoryPlayer slot
	// numbers 9 - 35)
	// 36 - 39 = fuel slots (tileEntity 0 - 3)
	// 40 - 44 = input slots (tileEntity 4 - 8)
	// 45 - 49 = output slots (tileEntity 9 - 13)

	private final int HOTBAR_SLOT_COUNT = 9;
	private final int PLAYER_INVENTORY_ROW_COUNT = 3;
	private final int PLAYER_INVENTORY_COLUMN_COUNT = 9;

	public final int FUEL_SLOTS_COUNT = 1;
	public final int BATTERY_SLOTS_COUNT = 1;

	// slot index is the unique index for all slots in this container i.e. 0 -
	// 35 for invPlayer then 36 - 49 for tileInventoryFurnace

	// slot number is the slot number within each component; i.e. invPlayer
	// slots 0 - 35, and tileInventoryFurnace slots 0 - 14
	private final int FIRST_FUEL_SLOT_NUMBER = 0;
	private final int FIRST_INPUT_SLOT_NUMBER = FIRST_FUEL_SLOT_NUMBER + FUEL_SLOTS_COUNT;

	public ContainerCharger(InventoryPlayer invPlayer, TileCharger tileInventoryFurnace) {
		this.tileInventoryFurnace = tileInventoryFurnace;

		final int SLOT_X_SPACING = 18;
		final int SLOT_Y_SPACING = 18;
		final int HOTBAR_XPOS = 8;
		final int HOTBAR_YPOS = 142;
		// Add the players hotbar to the gui - the [xpos, ypos] location of each
		// item
		for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
			int slotNumber = x;
			addSlotToContainer(new Slot(invPlayer, slotNumber, HOTBAR_XPOS + SLOT_X_SPACING * x, HOTBAR_YPOS));
		}

		final int PLAYER_INVENTORY_XPOS = 8;
		final int PLAYER_INVENTORY_YPOS = 84;
		// Add the rest of the players inventory to the gui
		for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
			for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
				int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
				int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
				int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
				addSlotToContainer(new Slot(invPlayer, slotNumber, xpos, ypos));
			}
		}

		final int FUEL_SLOTS_XPOS = 12;
		final int FUEL_SLOTS_YPOS = 60;
		// Add the tile fuel slots
		for (int x = 0; x < FUEL_SLOTS_COUNT; x++) {
			int slotNumber = x + FIRST_FUEL_SLOT_NUMBER;
			addSlotToContainer(new SlotFuel(tileInventoryFurnace, slotNumber, FUEL_SLOTS_XPOS + SLOT_X_SPACING * x,
					FUEL_SLOTS_YPOS));
		}

		final int BATTERY_SLOTS_XPOS = 80;
		final int BATTERY_SLOTS_YPOS = 34;
		// Add the tile input slots
		for (int y = 0; y < BATTERY_SLOTS_COUNT; y++) {
			int slotNumber = y + FIRST_INPUT_SLOT_NUMBER;
			addSlotToContainer(new SlotBattery(tileInventoryFurnace, slotNumber, BATTERY_SLOTS_XPOS,
					BATTERY_SLOTS_YPOS + SLOT_Y_SPACING * y));
		}
	}

	// Checks each tick to make sure the player is still able to access the
	// inventory and if not closes the gui
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileInventoryFurnace.isUseableByPlayer(player);
	}

	// This is where you specify what happens when a player shift clicks a slot
	// in the gui
	// (when you shift click a slot in the TileEntity Inventory, it moves it to
	// the first available position in the hotbar and/or
	// player inventory. When you you shift-click a hotbar or player inventory
	// item, it moves it to the first available
	// position in the TileEntity inventory - either input or fuel as
	// appropriate for the item you clicked)
	// At the very least you must override this and return null or the game will
	// crash when the player shift clicks a slot
	// returns null if the source slot is empty, or if none of the source slot
	// items could be moved.
	// otherwise, returns a copy of the source stack
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int sourceSlotIndex) {
		// Slot sourceSlot = (Slot) inventorySlots.get(sourceSlotIndex);
		// if (sourceSlot == null || !sourceSlot.getHasStack())
		// return null;
		// ItemStack sourceStack = sourceSlot.getStack();
		// ItemStack copyOfSourceStack = sourceStack.copy();

		return null;
	}

	/* Client Synchronization */

	// This is where you check if any values have changed and if so send an
	// update to any clients accessing this container
	// The container itemstacks are tested in Container.detectAndSendChanges, so
	// we don't need to do that
	// We iterate through all of the TileEntity Fields to find any which have
	// changed, and send them.
	// You don't have to use fields if you don't wish to; just manually match
	// the ID in sendProgressBarUpdate with the value in
	// updateProgressBar()
	// The progress bar values are restricted to shorts. If you have a larger
	// value (eg int), it's not a good idea to try and split it
	// up into two shorts because the progress bar values are sent
	// independently, and unless you add synchronisation logic at the
	// receiving side, your int value will be wrong until the second short
	// arrives. Use a custom packet instead.
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		boolean allFieldsHaveChanged = false;
		boolean fieldHasChanged[] = new boolean[tileInventoryFurnace.getFieldCount()];
		if (cachedFields == null) {
			cachedFields = new int[tileInventoryFurnace.getFieldCount()];
			allFieldsHaveChanged = true;
		}
		for (int i = 0; i < cachedFields.length; ++i) {
			if (allFieldsHaveChanged || cachedFields[i] != tileInventoryFurnace.getField(i)) {
				cachedFields[i] = tileInventoryFurnace.getField(i);
				fieldHasChanged[i] = true;
			}
		}

		// go through the list of listeners (players using this container) and
		// update them if necessary
		for (IContainerListener listener : this.listeners) {
			for (int fieldID = 0; fieldID < tileInventoryFurnace.getFieldCount(); ++fieldID) {
				if (fieldHasChanged[fieldID]) {
					// Note that although sendProgressBarUpdate takes 2 ints on
					// a server these are truncated to shorts
					listener.sendProgressBarUpdate(this, fieldID, cachedFields[fieldID]);
				}
			}
		}
	}

	// Called when a progress bar update is received from the server. The two
	// values (id and data) are the same two
	// values given to sendProgressBarUpdate. In this case we are using fields
	// so we just pass them to the tileEntity.
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int data) {
		tileInventoryFurnace.setField(id, data);
	}

	// SlotFuel is a slot for fuel items
	public class SlotFuel extends Slot {
		public SlotFuel(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		// if this function returns false, the player won't be able to insert
		// the given item into this slot
		@Override
		public boolean isItemValid(ItemStack stack) {
			return TileEntityFurnace.isItemFuel(stack);
		}
	}

	// SlotSmeltableInput is a slot for input items
	public class SlotBattery extends Slot {
		public SlotBattery(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		// if this function returns false, the player won't be able to insert
		// the given item into this slot
		@Override
		public boolean isItemValid(ItemStack stack) {
			return stack.getItem() instanceof ItemBattery;
		}
	}
}
