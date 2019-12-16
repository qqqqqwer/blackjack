package PSP;

final class Settings {

    private Settings() {};

    static final int WINDOW_WIDTH = 1200;
    static final int WINDOW_HEIGHT = 800;
    static final int STARTING_CASH = 50;
    static final int WIN_CONDITION = 300;
    static final int LOSE_CONDITION = 0;
    static final int ROUND_GOAL = 21;

    private static final int FULL_IMAGE_WIDTH = 691;
    private static final int FULL_IMAGE_HEIGHT = 1056;
    private static final double SCALE = 6;
    static final double  IMAGE_WIDTH = FULL_IMAGE_WIDTH / SCALE;
    static final double IMAGE_HEIGHT = FULL_IMAGE_HEIGHT / SCALE;

    static final double BLACKJACK_PAY = 2.5;
    static final double NORMAL_PAY = 2;
    static final double INSURANCE_PAY = 2;

}
