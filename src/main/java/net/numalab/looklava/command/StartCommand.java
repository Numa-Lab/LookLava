package net.numalab.looklava.command;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.numalab.looklava.Config;

public class StartCommand extends Command {
    public StartCommand() {
        super("start");
    }

    @Override
    public void execute(CommandContext ctx) {
        if (Config.isEnabled) {
            ctx.fail("LookLavaは既に有効です.");
            return;
        }

        Config.isEnabled = true;
        ctx.success("LookLavaを有効化しました.");
    }
}