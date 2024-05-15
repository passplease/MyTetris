package Project.GUI;

import static Project.GUI.MainFrame.nextBlock;

public class I_Shape_Blocks implements Blocks {
    private static int sequence;
    @Override
    public void rotateClockwise() {

    }

    @Override
    public void rotateCounterClockwise() {

    }

    @Override
    public void show() {

    }

    @Override
    public void solidified() {

    }

    @Override
    public boolean examineDrop() {
        return false;
    }

    @Override
    public boolean examineNow() {
        return false;
    }

    @Override
    public void readyShow() {
        nextBlock = allBlocks.get(sequence);
    }

    private static void initialize() {
        sequence = allBlocks.size();
        allBlocks.put(allBlocks.size(),new I_Shape_Blocks());
    }

    public static void register() {
        Blocks.Register(I_Shape_Blocks::initialize);
    }
}
