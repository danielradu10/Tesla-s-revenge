package utile;

public class Constante {

    public static class Directions{
        public static final int LEFT = 0;
        public static final int RIGHT =3;
        public static final int UP = 1;

        public static final int DOWN = 2;
    }
    public static class HeroConstants{
        public static final int IDLE = 0;
        public static final int IDLE_AIMING = 1;
        public static final int JUMP = 2;

        public static final int JUMP_AIM = 3;
        public static final int RUN = 4;
        public static final int RUN_AIM = 5;
        public static final int SHOOT = 6;
        public static final int SLIDE = 7;
        public static final int WALK = 8;
        public static final int WALK_AIM = 9;

        public static final int HURT = 10;
        public static final int DEAD = 11;
        public static final int VICTORY = 12;

        public static int GetSpriteAmount(int player_action){
            switch(player_action){
                case IDLE: return 10;
                case IDLE_AIMING: return 10;
                case JUMP: return 20;
                case JUMP_AIM: return 20;
                case RUN: return 8;
                case RUN_AIM: return 8;
                case SHOOT: return 8;
                case SLIDE: return 5;
                case WALK: return 10;
                case WALK_AIM: return 10;
                case HURT: return 10;
                case DEAD: return 10;
                case VICTORY: return 10;
                default: return 5;
            }

        }

    }

    public static class TileMap_Composition
    {
        public final static int SINGLE_PLATFORM = 0;
        public static final int BIG_PLATFORM = 1;

        public static final int NORMAL_PLATFORM = 2;

        public static String Get_List_Of_components(int platform)
        {
            String component;
            if(platform == 0)
            {
                component = "/Map/big_platform.png";
                return component;
            }
            if(platform == 1)
            {
                component = "/Map/platform.png";
                return component;
            }

            if(platform == 2)
            {
                component ="/Map/longer_platform.png";
                return component;
            }

            if(platform == 3)
            {
                component = "/BrownMap/long_platform.png";
                return component;
            }

            return null;
        }

    }

    public static class TurtleConstants
    {
        public static int WALK = 0;
        public static int DEAD = 1;
        public static int get_turtle_dimension(int action)
        {
            if(action == WALK)
            {
                return 10;
            }

            if(action == DEAD)
            {
                return 8;
            }

            return 0;
        }
    }

    public static class SeahorseConstants
    {
        public static int MOVE = 0;
        public static int ATTACK = 1;

        public static int DEAD = 2;
        public static int get_seahorse_dimension(int action)
        {
            if(action == MOVE)
            {
                return 5;
            }

            if(action == ATTACK)
            {
                return 9;
            }

            if(action == DEAD)
            {
                return 9;
            }

            return 0;
        }

    }

    public static class GameStates
    {
        public static int MENIU = 0;

        public static int USERNAME = 1;


        public static int LEVEL_ONE = 2;

        public static int LEVEL_two = 3;

        public static int MENUSTATE = 4;

        public static int QUIZ = 5;
    }


    public static class FulgerStates
    {
        public static int BOLT1 = 0;
        public static int BOLT2 = 1;
        public static int BOLT3 = 2;
        public static int BOLT4 = 3;

        public static int getSpriteAmount(int anim)
        {
            if(anim == BOLT1)
            {
                return 6;
            }
            if(anim == BOLT2)
            {
                return 8;
            }
            if(anim == BOLT3)
            {
                return 6;
            }
            if(anim == BOLT4)
            {
                return 6;
            }
            return 0;
        }


    }


}
