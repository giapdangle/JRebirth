package org.jrebirth.af.dialog;

import org.jrebirth.af.api.concurrent.RunInto;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.annotation.OnWave;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.command.single.AbstractSingleCommand;
import org.jrebirth.af.core.exception.CommandException;
import org.jrebirth.af.core.wave.Builders;
import org.jrebirth.af.processor.annotation.WarmUp;

@WarmUp
@RunInto(RunType.JIT)
public class DialogDaemonCommand extends AbstractSingleCommand<DialogWB> {

    public static final String OPEN_DIALOG_WT = "OPEN_DIALOG";

    private final WaveType OPEN_DIALOG = Builders.waveType(OPEN_DIALOG_WT);

    @Override
    protected void initCommand() {
        listen(OPEN_DIALOG);
    }

    @Override
    protected void perform(Wave wave) throws CommandException {
        callCommand(DialogCommand.class, getWaveBean(wave));
    }

    @Override
    protected void initInnerComponents() {
        // Nothing to do yet

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @OnWave(OPEN_DIALOG_WT)
    protected void processWave(final Wave wave) {
        if (OPEN_DIALOG.equals(wave.waveType())) {

            callCommand(DialogCommand.class, getWaveBean(wave));

        }
    }

}
