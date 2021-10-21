package multiteam.multiexpansion.mixin;

//TODO THIS CLASS WILL BE MIGRATED TO MULTICORELIB, ITS JUST HERE FOR DEV PURPOSES!
public class RenderingTool {


    //TODO move to MathF
    public static int clampInt(int input, int min, int max){
        if(input > max){
            return max;
        }
        if(input < min){
            return min;
        }
        return input;
    }
}
