package com.elmakers.mine.bukkit.action;

import com.elmakers.mine.bukkit.api.action.SpellAction;
import com.elmakers.mine.bukkit.api.action.CastContext;
import com.elmakers.mine.bukkit.api.magic.Mage;
import com.elmakers.mine.bukkit.api.spell.SpellResult;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Collection;

public abstract class CompoundAction extends BaseSpellAction
{
	private boolean usesBrush = false;
	private boolean undoable = false;
    private boolean requiresBuildPermission = false;
	protected ActionHandler actions = null;

	@Override
	public void initialize(ConfigurationSection parameters)
	{
		super.initialize(parameters);

		usesBrush = false;
		undoable = false;
        requiresBuildPermission = false;
        if (parameters != null)
        {
            if (parameters.contains("actions"))
            {
                actions = new ActionHandler();
                actions.load(parameters, "actions");
            }
        }
        if (actions != null)
        {
            actions.initialize(parameters);
            updateFlags();
        }
    }

    protected void updateFlags() {
        usesBrush = usesBrush || actions.usesBrush();
        undoable = undoable || actions.isUndoable();
        requiresBuildPermission = requiresBuildPermission || actions.requiresBuildPermission();
    }

    public void addAction(SpellAction action) {
        addAction(action, null);
    }

    public void addAction(SpellAction action, ConfigurationSection parameters) {
        if (actions == null) {
            actions = new ActionHandler();
        }
        actions.loadAction(action, parameters);
        updateFlags();
    }

	protected SpellResult performActions(CastContext context) {
		if (actions == null) {
			return SpellResult.FAIL;
		}
		return actions.perform(context);
	}

    @Override
    public void load(Mage mage, ConfigurationSection data)
    {
        if (actions != null)
        {
            actions.loadData(mage, data);
        }
    }

    @Override
    public void save(Mage mage, ConfigurationSection data)
    {
        if (actions != null)
        {
            actions.saveData(mage, data);
        }
    }

    @Override
    public void finish(CastContext context) {
        actions.finish(context);
    }

    @Override
    public void prepare(CastContext context, ConfigurationSection parameters) {
        super.prepare(context, parameters);
        actions.prepare(context, parameters);
    }

	@Override
	public boolean isUndoable()
	{
		return undoable;
	}

	@Override
	public boolean usesBrush()
	{
		return usesBrush;
	}

    @Override
    public boolean requiresBuildPermission()
    {
        return requiresBuildPermission;
    }

	@Override
	public void getParameterNames(Collection<String> parameters)
	{
		if (actions != null)
		{
			actions.getParameterNames(parameters);
		}
	}

	@Override
	public void getParameterOptions(Collection<String> examples, String parameterKey)
	{
		if (actions != null)
		{
			actions.getParameterOptions(examples, parameterKey);
		}
	}

	@Override
	public String transformMessage(String message)
	{
		if (actions == null)
		{
			return message;
		}
		return actions.transformMessage(message);
	}

    public CastContext createContext(CastContext context) {
        return new com.elmakers.mine.bukkit.action.CastContext(context);
    }
}
