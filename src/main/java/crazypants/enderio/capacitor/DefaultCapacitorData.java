package crazypants.enderio.capacitor;

import javax.annotation.Nonnull;

import crazypants.enderio.EnderIO;

public enum DefaultCapacitorData implements ICapacitorData {

  BASIC_CAPACITOR("basic", 1),
  ACTIVATED_CAPACITOR("activated", 2),
  ENDER_CAPACITOR("ender", 3),
  SPECIAL_CAPACITOR("special", 1);

  public static final @Nonnull ICapacitorData NONE = new ICapacitorData() {

    @Override
    public int getBaseLevel() {
      return 0;
    }

    @Override
    public float getUnscaledValue(@Nonnull ICapacitorKey key) {
      return 0;
    }

    @Override
    @Nonnull
    public String getUnlocalizedName() {
      return "none";
    }

    @Override
    @Nonnull
    public String getLocalizedName() {
      return "none";
    }

  };

  private final @Nonnull String unlocalizedName;
  private final int baselevel;

  private DefaultCapacitorData(@Nonnull String unlocalizedName, int baselevel) {
    this.unlocalizedName = unlocalizedName;
    this.baselevel = baselevel;
  }

  @Override
  public @Nonnull String getUnlocalizedName() {
    return unlocalizedName;
  }

  @Override
  public @Nonnull String getLocalizedName() {
    return EnderIO.lang.localize(getUnlocalizedName() + ".name");
  }

  @Override
  public float getUnscaledValue(@Nonnull ICapacitorKey key) {
    return baselevel;
  }

  @Override
  public int getBaseLevel() {
    return baselevel;
  }

}