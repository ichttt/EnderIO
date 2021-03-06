package crazypants.enderio.conduits.me.init;

import java.util.function.BiFunction;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.enderio.core.common.util.NullHelper;

import crazypants.enderio.api.IModObject;
import crazypants.enderio.api.IModTileEntity;
import crazypants.enderio.base.init.IModObjectBase;
import crazypants.enderio.base.init.ModObjectRegistry;
import crazypants.enderio.base.init.RegisterModObject;
import crazypants.enderio.conduits.me.conduit.ItemMEConduit;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public enum ConduitAppliedEnergisticsObject implements IModObjectBase {

  item_me_conduit(ItemMEConduit::create);

  ;

  public static void registerBlocksEarly(@Nonnull RegisterModObject event) {
    event.register(ConduitAppliedEnergisticsObject.class);
  }

  final @Nonnull String unlocalisedName;

  protected @Nullable Block block;
  protected @Nullable Item item;

  protected final @Nullable IModTileEntity modTileEntity;

  protected final @Nullable Function<IModObject, Block> blockMaker;
  protected final @Nullable BiFunction<IModObject, Block, Item> itemMaker;

  private ConduitAppliedEnergisticsObject(@Nonnull BiFunction<IModObject, Block, Item> itemMaker) {
    this(null, itemMaker, null);
  }

  private ConduitAppliedEnergisticsObject(@Nonnull Function<IModObject, Block> blockMaker) {
    this(blockMaker, null, null);
  }

  private ConduitAppliedEnergisticsObject(@Nonnull Function<IModObject, Block> blockMaker, @Nonnull BiFunction<IModObject, Block, Item> itemMaker) {
    this(blockMaker, itemMaker, null);
  }

  private ConduitAppliedEnergisticsObject(@Nonnull Function<IModObject, Block> blockMaker, @Nonnull IModTileEntity modTileEntity) {
    this(blockMaker, null, modTileEntity);
  }

  private ConduitAppliedEnergisticsObject(@Nullable Function<IModObject, Block> blockMaker, @Nullable BiFunction<IModObject, Block, Item> itemMaker,
      @Nullable IModTileEntity modTileEntity) {
    this.unlocalisedName = ModObjectRegistry.sanitizeName(NullHelper.notnullJ(name(), "Enum.name()"));
    this.blockMaker = blockMaker;
    this.itemMaker = itemMaker;
    if (blockMaker == null && itemMaker == null) {
      throw new RuntimeException(this + " unexpectedly is neither a Block nor an Item.");
    }
    this.modTileEntity = modTileEntity;
  }

  @Override
  public final @Nonnull String getUnlocalisedName() {
    return unlocalisedName;
  }

  @Override
  public final @Nullable Block getBlock() {
    return block;
  }

  @Override
  public final @Nullable Item getItem() {
    return item;
  }

  @Override
  public final @Nullable Class<?> getClazz() {
    return null;
  }

  @Override
  public final String getBlockMethodName() {
    return null;
  }

  @Override
  public final String getItemMethodName() {
    return null;
  }

  @Override
  public final void setItem(@Nullable Item obj) {
    item = obj;
  }

  @Override
  public final void setBlock(@Nullable Block obj) {
    block = obj;
  }

  @Override
  @Nullable
  public IModTileEntity getTileEntity() {
    return modTileEntity;
  }

  @Override
  public @Nonnull Function<IModObject, Block> getBlockCreator() {
    return blockMaker != null ? blockMaker : mo -> null;
  }

  @Override
  public @Nonnull BiFunction<IModObject, Block, Item> getItemCreator() {
    return NullHelper.first(itemMaker, IModObject.WithBlockItem.itemCreator);
  }

}
