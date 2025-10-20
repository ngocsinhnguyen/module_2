package s8Clean_Code.TennisGame;

public class TennisGame {
    private static final int MIN_WINNING_SCORE = 4;
    private static final int SCORE_DIFFERENCE_FOR_WIN = 2;

    private int player1Score;
    private int player2Score;
    private final String player1Name;
    private final String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }


    public void wonPoint(String playerName) {
        if (playerName.equals(this.player1Name)) {
            player1Score++;
        } else if (playerName.equals(this.player2Name)) {
            player2Score++;
        }
    }

    public String getScore() {
        if (isDraw()) {
            return getDrawScore();
        }

        if (isEndGame()) {
            return getEndGameScore();
        }

        return getNormalScore();
    }


    private boolean isDraw() {
        return player1Score == player2Score;
    }

    private boolean isEndGame() {
        return player1Score >= MIN_WINNING_SCORE || player2Score >= MIN_WINNING_SCORE;
    }

    private String getDrawScore() {
        return switch (player1Score) {
            case 0 -> "Love-All";
            case 1 -> "Fifteen-All";
            case 2 -> "Thirty-All";
            case 3 -> "Forty-All";
            default -> "Deuce";
        };
    }

    private String getEndGameScore() {
        int scoreDifference = player1Score - player2Score;

        if (scoreDifference == 1) return "Advantage " + player1Name;
        if (scoreDifference == -1) return "Advantage " + player2Name;
        if (scoreDifference >= SCORE_DIFFERENCE_FOR_WIN) return "Win for " + player1Name;
        return "Win for " + player2Name;
    }

    private String getNormalScore() {
        return pointToText(player1Score) + "-" + pointToText(player2Score);
    }

    private String pointToText(int score) {
        return switch (score) {
            case 0 -> "Love";
            case 1 -> "Fifteen";
            case 2 -> "Thirty";
            case 3 -> "Forty";
            default -> "";
        };
    }


    public static void main(String[] args) {
        TennisGame game = new TennisGame("player1", "player2");

        game.wonPoint("player1");
        game.wonPoint("player2");
        game.wonPoint("player1");
        game.wonPoint("player1");

        System.out.println("Current Score: " + game.getScore());
    }
}


// những thay đổi
// Biến: m_score1, m_score2    ->        player1Score, player2Score
// Number 2,3,4                ->        dùng hằng MIN_WINNING_SCORE, SCORE_DIFFERENCE_FOR_WIN
// Phương thức dài, nhiều if-else     ->        tách thành getDrawScore, getEndGameScore, getNormalScore
// Điều kiện                   -> tách thành isDraw, isEndGame