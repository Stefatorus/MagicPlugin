package com.elmakers.mine.bukkit.api.action;

import com.elmakers.mine.bukkit.api.block.MaterialBrush;
import com.elmakers.mine.bukkit.api.block.UndoList;
import com.elmakers.mine.bukkit.api.effect.EffectPlay;
import com.elmakers.mine.bukkit.api.effect.EffectPlayer;
import com.elmakers.mine.bukkit.api.magic.Mage;
import com.elmakers.mine.bukkit.api.magic.MageController;
import com.elmakers.mine.bukkit.api.spell.Spell;
import com.elmakers.mine.bukkit.api.spell.SpellResult;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

public interface CastContext {
    public Entity getEntity();
    public LivingEntity getLivingEntity();
    public Location getLocation();
    public Location getTargetLocation();
    public Location getTargetSourceLocation();
    public Vector getDirection();
    public void setDirection(Vector direction);
    public World getWorld();
    public Plugin getPlugin();
    public Location getEyeLocation();
    public Location getWandLocation();
    public Block getTargetBlock();
    public Block getInteractBlock();
    public Entity getTargetEntity();
    public void setTargetEntity(Entity targetEntity);
    public void setTargetLocation(Location targetLocation);
    public void setTargetSourceLocation(Location targetLocation);
    public Spell getSpell();
    public Mage getMage();
    public Collection<EffectPlayer> getEffects(String key);
    public MageController getController();
    public void registerForUndo(Runnable runnable);
    public void registerModified(Entity entity);
    public void registerForUndo(Entity entity);
    public void registerForUndo(Block block);
    public void updateBlock(Block block);
    public void registerVelocity(Entity entity);
    public void registerMoved(Entity entity);
    public void registerPotionEffects(Entity entity);
    public void registerBreakable(Block block, int breakable);
    public void registerReflective(Block block, double reflectivity);
    public Block getPreviousBlock();
    public boolean isIndestructible(Block block);
    public boolean hasBuildPermission(Block block);
    public boolean hasBreakPermission(Block block);
    public void playEffects(String key);
    public void playEffects(String key, float scale);
    public void cancelEffects();
    public String getMessage(String key);
    public String getMessage(String key, String def);
    public Location findPlaceToStand(Location target, int verticalSearchDistance);
    public Location findPlaceToStand(Location target, int verticalSearchDistance, boolean goUp);
    public void castMessage(String message);
    public void sendMessage(String message);
    public void castMessageKey(String key);
    public void sendMessageKey(String key);
    public void setTargetedLocation(Location location);
    public Block findBlockUnder(Block block);
    public Block findSpaceAbove(Block block);
    public boolean isTransparent(Material material);
    public boolean isPassthrough(Material material);
    public boolean isDestructible(Block block);
    public boolean areAnyDestructible(Block block);
    public boolean isTargetable(Material material);
    public boolean canTarget(Entity entity);
    public MaterialBrush getBrush();
    public void setBrush(MaterialBrush brush);
    public Collection<Entity> getTargetedEntities();
    public void getTargetEntities(int count, Collection<WeakReference<Entity>> entities);
    public void messageTargets(String messageKey);
    public Random getRandom();
    public UndoList getUndoList();
    public void setTargetName(String name);
    public String getTargetName();
    public Logger getLogger();
    public int getWorkAllowed();
    public void setWorkAllowed(int work);
    public void addWork(int work);
    public void performedActions(int count);
    public int getActionsPerformed();
    public void finish();
    public void retarget(int range, double fov, double closeRange, double closeFOV, boolean useHitbox);
    public void retarget(int range, double fov, double closeRange, double closeFOV, boolean useHitbox, Vector offset, boolean targetSpaceRequired, int targetMinOffset);
    public CastContext getBaseContext();
    public Set<UUID> getTargetMessagesSent();
    public Collection<EffectPlay> getCurrentEffects();
    public void teleport(final Entity entity, final Location location, final int verticalSearchDistance);
    public boolean allowPassThrough(Material material);
    public int getVerticalSearchDistance();
    public boolean isOkToStandIn(Material mat);
    public boolean isWater(Material mat);
    public boolean isOkToStandOn(Material mat);
    public Set<Material> getMaterialSet(String key);
    public void setSpellParameters(ConfigurationSection parameters);
    public SpellResult getResult();
    public void setResult(SpellResult result);
    public void addResult(SpellResult result);
    public boolean getTargetsCaster();
    public void setTargetsCaster(boolean target);
}
