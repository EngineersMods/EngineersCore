package engineers.core.blocks.machines.charger;

import javax.annotation.Nullable;

import engineers.core.EngineersCore;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCharger extends Block implements ITileEntityProvider {

	public BlockCharger() {
		super(Material.PISTON);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileCharger();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote)
			return true;

		playerIn.openGui(EngineersCore.instance, GuiHandlerCharger.getGuiID(), worldIn, pos.getX(), pos.getY(),
				pos.getZ());
		return true;
	}

}
