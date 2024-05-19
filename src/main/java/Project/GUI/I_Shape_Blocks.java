package Project.GUI;

public class I_Shape_Blocks extends Blocks {
    private static int sequence = 0;

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

    }

    private static void initialize() {
        sequence = allBlocks.size();
        allBlocks.put(allBlocks.size(),new I_Shape_Blocks());
    }

    public static void register() {
        Blocks.Register(I_Shape_Blocks::initialize);
    }
}