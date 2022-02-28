package net.numalab.looklava.command;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.numalab.looklava.Config;

public class StopCommand extends Command {
    public StopCommand() {
        super("stop");
    }

    @Override
    public void execute(CommandContext ctx) {
        if (!Config.isEnabled) {
            ctx.fail("LookLavaは既に無効です.");
            return;
        }

        Config.isEnabled = false;
        ctx.success("LookLavaを無効化しました.");
    }
}