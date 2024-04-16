import javax.naming.directory.SearchControls;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class Evalueator {

    public float EvaluatePosition(BoardState board) {
        return 0;
    }
}

class PiecesValue extends Evalueator {
    @Override
    public float EvaluatePosition(BoardState board) {
        float r = 0;
        for (int i = 0; i < board.Board.length; i++) {
            for (int j = 0; j < board.Board[i].length; j++) {
                String piece = board.GetText(i, j);
                if (!piece.equals("")) {
                    switch (piece) {
                        case "\u2656":
                            r += 5;
                            break;
                        case "\u265C":
                            r -= 5;
                            break;
                        case "\u2658":
                            r += 3;
                            break;
                        case "\u265E":
                            r -= 3;
                            break;
                        case "\u2657":
                            r += 3.5f;
                            break;
                        case "\u265D":
                            r -= 3.5f;
                            break;
                        case "\u2655":
                            r += 9;
                            break;
                        case "\u265B":
                            r -= 9;
                            break;
                        case "\u2659":
                            if (i == 1)
                                r += 3;
                            r += 1;
                            break;
                        case "\u265F":
                            if (i == 6)
                                r -= 3;
                            r -= 1;
                            break;
                    }
                }
            }
        }
        if (Chess.IsCheck(1, null, board)) {
            r += 0.5;
            if (Chess.IsCheckMate(1, board))
                r += 100;
        } else if (Chess.IsCheckMate(1, board))
            r = 0;
        if (Chess.IsCheck(-1, null, board)) {
            r -= 0.5;
            if (Chess.IsCheckMate(-1, board))
                r -= 100;
        } else if (Chess.IsCheckMate(-1, board))
            r = 0;
        return r;
    }
}

class EndGameDifference extends Evalueator {
    @Override
    public float EvaluatePosition(BoardState board) {
        float r = 0;
        float n = board.GetNumberOfPieces() * 0.07142f - 0.0142857f;
        if (n < 0)
            n = 0;
        for (int i = 0; i < board.Board.length; i++) {
            for (int j = 0; j < board.Board[i].length; j++) {
                String piece = board.GetText(i, j);
                if (!piece.equals("")) {
                    switch (piece) {
                        case "\u2656":
                            r += 4 * n + (1 - n) * 6;
                            break;
                        case "\u265C":
                            r -= 4 * n + (1 - n) * 6;
                            break;
                        case "\u2658":
                            r += 3 * n + (1 - n) * 2.5;
                            break;
                        case "\u265E":
                            r -= 3 * n + (1 - n) * 2.5;
                            break;
                        case "\u2657":
                            r += 3 * n + (1 - n) * 3.5;
                            break;
                        case "\u265D":
                            r -= 3 * n + (1 - n) * 3.5;
                            break;
                        case "\u2655":
                            r += 9 * n + (1 - n) * 10;
                            break;
                        case "\u265B":
                            r -= 9 * n + (1 - n) * 10;
                            break;
                        case "\u2659":
                            if (i == 1)
                                r += 3;
                            r += 1 * n + (1 - n) * 2.5;
                            break;
                        case "\u265F":
                            if (i == 6)
                                r -= 3;
                            r -= 1 * n + (1 - n) * 2.5;
                            break;
                    }
                }
            }
        }
        if (Chess.IsCheck(1, null, board)) {
            r += 0.5;
            if (Chess.IsCheckMate(1, board))
                r += 100;
        } else if (Chess.IsCheckMate(1, board))
            r = 0;
        if (Chess.IsCheck(-1, null, board)) {
            r -= 0.5;
            if (Chess.IsCheckMate(-1, board))
                r -= 100;
        } else if (Chess.IsCheckMate(-1, board))
            r = 0;
        return r;
    }
}

class Movility extends Evalueator {

    @Override
    public float EvaluatePosition(BoardState board) {
        float r = 0;
        float n = board.GetNumberOfPieces() * 0.07142f - 0.0142857f;
        if (n < 0)
            n = 0;
        for (int i = 0; i < board.Board.length; i++) {
            for (int j = 0; j < board.Board[i].length; j++) {
                String piece = board.GetText(i, j);
                if (!piece.equals("")) {
                    switch (piece) {
                        case "\u2656":
                            r += 4 * n + (1 - n) * 6;
                            break;
                        case "\u265C":
                            r -= 4 * n + (1 - n) * 6;
                            break;
                        case "\u2658":
                            r += 3 * n + (1 - n) * 2.5;
                            break;
                        case "\u265E":
                            r -= 3 * n + (1 - n) * 2.5;
                            break;
                        case "\u2657":
                            r += 3 * n + (1 - n) * 3.5;
                            break;
                        case "\u265D":
                            r -= 3 * n + (1 - n) * 3.5;
                            break;
                        case "\u2655":
                            r += 9 * n + (1 - n) * 10;
                            break;
                        case "\u265B":
                            r -= 9 * n + (1 - n) * 10;
                            break;
                        case "\u2659":
                            if (i == 1)
                                r += 3;
                            r += 1 * n + (1 - n) * 2.5;
                            break;
                        case "\u265F":
                            if (i == 6)
                                r -= 3;
                            r -= 1 * n + (1 - n) * 2.5;
                            break;
                    }
                }
            }
        }
        for (PieceActionListener piece : board.GetPieces(-1)) {
            r += piece.GetAvailableMoves(board, false).size() * 0.01f;
        }
        for (PieceActionListener piece : board.GetPieces(1)) {
            r -= piece.GetAvailableMoves(board, false).size() * 0.01f;
        }

        if (Chess.IsCheck(1, null, board)) {
            r += 0.5;
            if (Chess.IsCheckMate(1, board))
                r += 100;
        } else if (Chess.IsCheckMate(1, board))
            r = 0;
        if (Chess.IsCheck(-1, null, board)) {
            r -= 0.5;
            if (Chess.IsCheckMate(-1, board))
                r -= 100;
        } else if (Chess.IsCheckMate(-1, board))
            r = 0;
        return r;
    }
}

class Center extends Evalueator {

    public float[][] CenterMult = {
            { 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1.2f, 1.2f, 1.2f, 1.2f, 1, 1 },
            { 1, 1, 1.2f, 1.3f, 1.3f, 1.2f, 1, 1 },
            { 1, 1, 1.2f, 1.3f, 1.3f, 1.2f, 1, 1 },
            { 1, 1, 1.2f, 1.2f, 1.2f, 1.2f, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1 }
    };

    @Override
    public float EvaluatePosition(BoardState board) {
        float r = 0;
        float n = board.GetNumberOfPieces() * 0.07142f - 0.0142857f;
        if (n < 0)
            n = 0;
        for (int i = 0; i < board.Board.length; i++) {
            for (int j = 0; j < board.Board[i].length; j++) {
                String piece = board.GetText(i, j);
                if (!piece.equals("")) {
                    switch (piece) {
                        case "\u2656":
                            r += (4 * n + (1 - n) * 6) * CenterMult[i][j];
                            break;
                        case "\u265C":
                            r -= (4 * n + (1 - n) * 6) * CenterMult[i][j];
                            break;
                        case "\u2658":
                            r += (3 * n + (1 - n) * 2.5) * CenterMult[i][j];
                            break;
                        case "\u265E":
                            r -= (3 * n + (1 - n) * 2.5) * CenterMult[i][j];
                            break;
                        case "\u2657":
                            r += (3 * n + (1 - n) * 3.5) * CenterMult[i][j];
                            break;
                        case "\u265D":
                            r -= (3 * n + (1 - n) * 3.5) * CenterMult[i][j];
                            break;
                        case "\u2655":
                            r += (9 * n + (1 - n) * 10) * CenterMult[i][j];
                            break;
                        case "\u265B":
                            r -= (9 * n + (1 - n) * 10) * CenterMult[i][j];
                            break;
                        case "\u2659":
                            if (i == 1)
                                r += 3;
                            r += (1 * n + (1 - n) * 2.5) * CenterMult[i][j];
                            break;
                        case "\u265F":
                            if (i == 6)
                                r -= 3;
                            r -= (1 * n + (1 - n) * 2.5) * CenterMult[i][j];
                            break;
                    }
                }
            }
        }
        for (PieceActionListener piece : board.GetPieces(-1)) {
            r += piece.GetAvailableMoves(board, false).size() * 0.01f;
        }
        for (PieceActionListener piece : board.GetPieces(1)) {
            r -= piece.GetAvailableMoves(board, false).size() * 0.01f;
        }

        if (Chess.IsCheck(1, null, board)) {
            r += 0.5;
            if (Chess.IsCheckMate(1, board))
                r += 100;
        } else if (Chess.IsCheckMate(1, board))
            r = 0;
        if (Chess.IsCheck(-1, null, board)) {
            r -= 0.5;
            if (Chess.IsCheckMate(-1, board))
                r -= 100;
        } else if (Chess.IsCheckMate(-1, board))
            r = 0;
        return r;
    }
}

class PieceMaps extends Evalueator {

    public float[][] KnightMap = {
            { -5, -4, -3, -3, -3, -3, -4, -5 },
            { -4, -2, 0, 0, 0, 0, -2, -4 },
            { -3, 0, 1, 1.5f, 1.5f, 1, 0, -3 },
            { -3, 0.5f, 1.5f, 2, 2, 1.5f, 0.5f, -3 },
            { -3, 0, 1.5f, 2, 2, 1.5f, 0, -3 },
            { -3, 0.5f, 3, 1.5f, 1.5f, 3, 0.5f, -3 },
            { -4, -2, 0, 0.5f, 0.5f, 0, -2, -4 },
            { -5, -4, -3, -3, -3, -3, -4, -5 }
    };
    public float[][] KingMap = {
            { -8, -7, -7, -7, -7, -7, -7, -8 },
            { -6, -6, -6, -6, -6, -6, -6, -6 },
            { -4, -5, -5, -6, -6, -5, -5, -4 },
            { -3, -4, -4, -5, -5, -4, -4, -3 },
            { -2, -3, -3, -4, -4, -3, -3, -2 },
            { -1, -2, 1, -2, -2, 1, -2, -1 },
            { 2, 2, -1, -1, -1, -1, 2, 2 },
            { 2, 3, 1, 0, 0, 1, 3, 2 }

    };
    public float[][] QueenMap = {
            { -5, -4, -3, -3, -3, -3, -4, -5 },
            { -4, -2, 0, 0, 0, 0, -2, -4 },
            { -3, 0, 1, 1.5f, 1.5f, 1, 0, -3 },
            { -3, 0, 1.5f, 2, 2, 1.5f, 0, -3 },
            { -3, 0, 1.5f, 2, 2, 1.5f, 0, -3 },
            { -3, 0, 1, 1.5f, 1.5f, 1, 0, -3 },
            { -4, -2, 0, 0, 0, 0, -2, -4 },
            { -5, -4, -3, -3, -3, -3, -4, -5 }
    };
    public float[][] RookMap = {
            { -3, -3, -3, -3, -3, -3, -3, -3 },
            { -2, 2, 2, 2, 2, 2, 2, -2 },
            { -5, -3, -3, -3, -3, -3, -3, -5 },
            { -5, -3, -3, -3, -3, -3, -3, -5 },
            { -5, -3, -3, -3, -3, -3, -3, -5 },
            { -5, -3, -3, -3, -3, -3, -3, -5 },
            { -5, -3, -3, -3, -3, -3, -3, -5 },
            { -3, -3, -2, -1, -1, -2, -3, -3 }

    };
    public float[][] BishopMap = {
            { -4, -3, -3, -3, -3, -3, -3, -4 },
            { -3, -2, -2, -2, -2, -2, -2, -4 },
            { -30, -2, -1, 2, 2, -1, -2, -30 },
            { -3, -1, -1, 2, 2, -1, -1, -3 },
            { -3, -2, 3, 2, 2, 3, -2, -3 },
            { -3, 2, 2, 2, 2, 2, 2, -3 },
            { -3, 2, -2, -2, -2, -2, 2, -3 },
            { -1.5f, -3, -3, -3, -3, -3, -3, -1.5f },
    };
    public float[][] PawnMap = {
            { -4, -4, -4, -4, -4, -4, -4, -4 },
            { 3, 3, 3, 3, 3, 3, 3, 3 },
            { -2, -2, 0, 1, 1, 0, -2, -2 },
            { -2, -2, 0, 1, 1, 0, -2, -2 },
            { -2, -2, -2, 1, 1, -2, -2, -2 },
            { -1, -4, -4, -4, -4, -4, -4, -4 },
            { -1, 0, -1, -6, -6, -1, 0, -1 },
            { -2, -2, -2, -2, -2, -2, -2, -2 }
    };

    @Override
    public float EvaluatePosition(BoardState board) {
        float r = 0;
        float n = board.GetNumberOfPieces() * 0.07142f - 0.0142857f;
        if (n < 0)
            n = 0;

        r += MaterialScore(board, n);

        if (Chess.IsCheck(1, null, board)) {
            r += 0.5;
            if (Chess.IsCheckMate(1, board))
                r += 100;
        } else if (Chess.IsCheckMate(1, board))
            r = 0;
        if (Chess.IsCheck(-1, null, board)) {
            r -= 0.5;
            if (Chess.IsCheckMate(-1, board))
                r -= 100;
        } else if (Chess.IsCheckMate(-1, board))
            r = 0;
        return r;
    }

    private float MaterialScore(BoardState board, float n) {
        float r = 0;

        for (int i = 0; i < board.Board.length; i++) {
            for (int j = 0; j < board.Board[i].length; j++) {
                String piece = board.GetText(i, j);
                if (!piece.equals("")) {
                    try {
                        switch (piece) {
                            case "\u2656":
                                r += (4 * n + (1 - n) * 6) + RookMap[i][j] / 5f;
                                break;
                            case "\u265C":
                                r -= (4 * n + (1 - n) * 6) + RookMap[7 - i][j] / 5f;
                                break;
                            case "\u2658":
                                r += (3 * n + (1 - n) * 2.5) + KnightMap[i][j] / 5f;
                                break;
                            case "\u265E":
                                r -= (3 * n + (1 - n) * 2.5) + KnightMap[7 - i][j] / 5f;
                                break;
                            case "\u2657":
                                r += (3 * n + (1 - n) * 3.5) + BishopMap[i][j] / 5f;
                                break;
                            case "\u265D":
                                r -= (3 * n + (1 - n) * 3.5) + BishopMap[7 - i][j] / 5f;
                                break;
                            case "\u2655":
                                r += (9 * n + (1 - n) * 10) + QueenMap[i][j] / 5f;
                                break;
                            case "\u265B":
                                r -= (9 * n + (1 - n) * 10) + QueenMap[7 - i][j] / 5f;
                                break;
                            case "\u2659":
                                r += (1 * n + (1 - n) * 2.5) + PawnMap[i][j] / 5f;
                                break;
                            case "\u265F":
                                r -= (1 * n + (1 - n) * 2.5) + PawnMap[7 - i][j] / 5f;
                                break;
                            case "\u2654":
                                r += 20 + KingMap[i][j] / 4f;
                                break;
                            case "\u265A":
                                r -= 20 + KingMap[7 - i][j] / 4f;
                                break;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("i: " + i + " j: " + j);
                    }
                }
            }
        }
        return r;
    }
}

class BoardState {
    public String[][] Board;
    public boolean KingMoved1 = false;
    public boolean LeftRook1 = false;
    public boolean RightRook1 = false;
    public boolean KingMoved2 = false;
    public boolean LeftRook2 = false;
    public boolean RightRook2 = false;
    public PieceMove LastMove = null;

    public BoardState() {
        this.Board = new String[8][8];
        for (int i = 0; i < this.Board.length; i++) {
            for (int j = 0; j < this.Board[i].length; j++) {
                Board[i][j] = "";
            }
        }
    }

    public BoardState(String[][] b) {
        this.Board = b;
    }

    public BoardState(BoardState b) {
        this.Board = new String[8][8];
        for (int i = 0; i < b.Board.length; i++) {
            for (int j = 0; j < b.Board[i].length; j++) {
                this.Board[i][j] = new String(b.Board[i][j]);
            }
        }
    }

    public String GetText(int y, int x) {
        if (y < 0 || y > 7 || x < 0 || x > 7)
            return "B";
        return Board[y][x];
    }

    public void SetText(int y, int x, String text) {
        Board[y][x] = text;
    }

    public boolean IsBlocked(int y, int x) {
        String s = GetText(y, x);
        if (s.equals("") || s == null)
            return false;
        else
            return true;
    }

    public boolean IsCapturable(int y, int x, int m) {
        String s = GetText(y, x);
        if (s.equals("") || s == null)
            return false;
        else if (s.equals("B"))
            return false;
        else {
            if (m == -1)
                return !Chess.IsWhite(s);
            else
                return Chess.IsWhite(s);
        }
    }

    public void SetCastle(boolean k1, boolean rl1, boolean rr1, boolean k2, boolean rl2, boolean rr2) {
        this.KingMoved1 = k1;
        this.LeftRook1 = rl1;
        this.RightRook1 = rr1;
        this.KingMoved2 = k2;
        this.LeftRook2 = rl2;
        this.RightRook2 = rr2;
    }

    public BoardState DoMove(PieceMove move) {
        if (move == null)
            return this;
        BoardState b = new BoardState();
        for (int i = 0; i < b.Board.length; i++) {
            for (int j = 0; j < b.Board[i].length; j++) {
                b.Board[i][j] = new String(Board[i][j]);
            }
        }
        b.SetCastle(KingMoved1, LeftRook1, RightRook1, KingMoved2, LeftRook2, RightRook2);
        b.LastMove = new PieceMove(move);
        switch (move.type) {
            case 1: // Move/Capture
                String piece = b.GetText(move.y, move.x);
                b.SetText(move.y, move.x, "");
                b.SetText(move.y2, move.x2, piece);
                break;
            case 2:// Pass
                piece = b.GetText(move.y, move.x);
                b.SetText(move.y, move.x, "");
                b.SetText(move.y, move.x2, "");
                b.SetText(move.y2, move.x2, piece);
                break;
            case 3:// promotion
                b.SetText(move.y, move.x, "");
                if (Chess.IsWhite(move.piece)) {
                    b.SetText(move.y2, move.x2, "\u2655");
                } else {
                    b.SetText(move.y2, move.x2, "\u265B");
                }
                break;
            case 4:// Castle
                piece = b.GetText(move.y, move.x);
                String piece2 = b.GetText(move.y2, move.x2);
                b.SetText(move.y, move.x + ((move.x2 > 3) ? 1 : -1), piece2);
                b.SetText(move.y2, move.x2 + ((move.x2 > 3) ? -1 : 2), piece);
                b.SetText(move.y, move.x, "");
                b.SetText(move.y2, move.x2, "");
                /*
                 * if (Chess.IsWhite(move.piece)) {
                 * if (move.x2 == 0) {
                 * b.SetCastle(true, true, RightRook1, KingMoved2, LeftRook2, RightRook2);
                 * } else {
                 * b.SetCastle(true, LeftRook1, true, KingMoved2, LeftRook2, RightRook2);
                 * }
                 * } else {
                 * if (move.x2 == 0) {
                 * b.SetCastle(KingMoved1, LeftRook1, RightRook1, true, true, RightRook2);
                 * } else {
                 * b.SetCastle(KingMoved1, LeftRook1, RightRook1, true, LeftRook2, true);
                 * }
                 * }
                 * break;
                 */
        }
        if (!GetText(7, 4).equals("\u2654"))
            KingMoved1 = true;
        if (!GetText(7, 0).equals("\u2656"))
            LeftRook1 = true;
        if (!GetText(7, 7).equals("\u2656"))
            RightRook1 = true;
        if (!GetText(0, 4).equals("\u265A"))
            KingMoved2 = true;
        if (!GetText(0, 0).equals("\u265C"))
            LeftRook2 = true;
        if (!GetText(0, 7).equals("\u265C"))
            RightRook2 = true;
        /*
         * String PieceCap = move.GetFlags(this, 1).CapturedPiece;
         * if (Chess.IsWhite(move.piece)) {
         * if (move.piece.equals("\u2654")) {
         * b.SetCastle(true, b.LeftRook1, b.RightRook1, b.KingMoved2, b.LeftRook2,
         * b.RightRook2);
         * } else if (move.piece.equals("\u2656") && move.y == 7 && move.x == 0) {
         * b.SetCastle(b.KingMoved1, true, b.RightRook1, b.KingMoved2, b.LeftRook2,
         * b.RightRook2);
         * } else if (move.piece.equals("ºu2656") && move.y == 7 && move.x == 7) {
         * b.SetCastle(b.KingMoved1, b.LeftRook1, true, b.KingMoved2, b.LeftRook2,
         * b.RightRook2);
         * }
         * if (PieceCap.equals("\u265C")) {
         * if (move.x2 == 7) {
         * b.SetCastle(b.KingMoved1, b.LeftRook1, RightRook1, b.KingMoved2, b.LeftRook2,
         * true);
         * } else if (move.x2 == 0) {
         * b.SetCastle(b.KingMoved1, b.LeftRook1, RightRook1, b.KingMoved2, true,
         * b.RightRook2);
         * }
         * }
         * } else {
         * if (move.piece.equals("\u265A")) {
         * b.SetCastle(b.KingMoved1, b.LeftRook1, b.RightRook1, true, b.LeftRook2,
         * b.RightRook2);
         * } else if (move.piece.equals("\u265C") && move.y == 0 && move.x == 0) {
         * b.SetCastle(b.KingMoved1, b.LeftRook1, b.RightRook1, b.KingMoved2, true,
         * b.RightRook2);
         * } else if (move.piece.equals("\u265C") && move.y == 0 && move.x == 7) {
         * b.SetCastle(b.KingMoved1, b.LeftRook1, b.RightRook1, b.KingMoved2,
         * b.LeftRook2, true);
         * }
         * if (PieceCap.equals("\u2656")) {
         * if (move.x2 == 7) {
         * b.SetCastle(b.KingMoved1, b.LeftRook1, true, b.KingMoved2, b.LeftRook2,
         * b.RightRook2);
         * } else if (move.x2 == 0) {
         * b.SetCastle(b.KingMoved1, true, RightRook1, b.KingMoved2, b.LeftRook2,
         * b.RightRook2);
         * }
         * }
         * }
         */
        return b;
    }

    // if m == -1 returns all White PieceActionListeners else all black
    public ArrayList<PieceActionListener> GetPieces(int m) {
        ArrayList<PieceActionListener> pieces = new ArrayList<PieceActionListener>();
        for (int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board[i].length; j++) {
                if (!GetText(i, j).equals("")) {
                    if (m == -1) {
                        if (Chess.IsWhite(GetText(i, j))) {
                            pieces.add(Chess.GetPieceAction(GetText(i, j), i, j, m));
                        }
                    } else {
                        if (!Chess.IsWhite(GetText(i, j))) {
                            pieces.add(Chess.GetPieceAction(GetText(i, j), i, j, m));
                        }
                    }
                }
            }
        }
        return pieces;
    }

    public int GetNumberOfPieces() {
        int r = 0;
        for (int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board[i].length; j++) {
                if (!GetText(i, j).equals("")) {
                    r++;
                }
            }
        }
        return r;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board[i].length; j++) {
                switch (GetText(i, j)) {
                    case "\u2656":
                        s += "R";
                        break;
                    case "\u265C":
                        s += "r";
                        break;
                    case "\u2658":
                        s += "N";
                        break;
                    case "\u265E":
                        s += "n";
                        break;
                    case "\u2657":
                        s += "B";
                        break;
                    case "\u265D":
                        s += "b";
                        break;
                    case "\u2655":
                        s += "Q";
                        break;
                    case "\u265B":
                        s += "q";
                        break;
                    case "\u2659":
                        s += "P";
                        break;
                    case "\u265F":
                        s += "p";
                        break;
                    case "\u2654":
                        s += "K";
                        break;
                    case "\u265A":
                        s += "k";
                        break;
                    default:
                        s += " ";
                        break;
                }
            }
            s += "\n";
        }
        return s;
    }

    public int toHash() {
        int hash = 0;
        for (int i = 0; i < Board.length; i++) {
            for (int j = 0; j < Board[i].length; j++) {
                if (Board[i][j] != null || !Board[i][j].equals("")) {
                    hash ^= Chess.zobristTable[i][j][Chess.GetPieceType(Board[i][j])][Chess
                            .IsWhite(Board[i][j]) ? 0
                                    : 1];
                }
            }
        }
        return hash;
    }
}

class PieceMove {
    public int y;
    public int x;
    public int y2;
    public int x2;
    public String piece;
    public int type;

    public PieceMove(PieceMove o) {
        this.piece = o.piece;
        this.y = o.y;
        this.x = o.x;
        this.y2 = o.y2;
        this.x2 = o.x2;
        this.type = o.type;
    }

    public PieceMove(int y, int x, int y2, int x2, String p, int type) {
        this.piece = p;
        this.y = y;
        this.x = x;
        this.y2 = y2;
        this.x2 = x2;
        this.type = type;
    }

    @Override
    public String toString() {
        return "x:" + x + " y: " + y + " x2: " + x2 + " y2: " + y2 + " type: " + type + " piece: "
                + Chess.GetPieceType(piece);
    }

    public MoveFlags GetFlags(BoardState board, int id) {
        MoveFlags f = new MoveFlags();
        switch (id) {
            case 1:
                switch (type) {
                    case 1:
                    case 3:
                        f.CapturedPiece = board.GetText(y2, x2);
                        break;
                    case 2:
                        f.CapturedPiece = board.GetText(y, x2);
                        break;
                    default:
                        f.CapturedPiece = "";
                }
                return f;
            case 2:
                f.promotion = (piece.equals("\u2659") && x2 == 0) || (piece.equals("\u265F") && x2 == 7);
                return f;
            case 3:
                f.castle = type == 4;
                return f;
            case 4:
                f.check = Chess.IsCheck(Chess.IsWhite(piece) ? -1 : 1, this, board);
                return f;
            default:
                return f;
        }

    }

    public MoveFlags GetFlags(BoardState board) {
        MoveFlags f = new MoveFlags();
        switch (type) {
            case 1:
            case 3:
                f.CapturedPiece = board.GetText(y2, x2);
                break;
            case 2:
                f.CapturedPiece = board.GetText(y, x2);
                break;
            default:
                f.CapturedPiece = "";
        }
        f.promotion = (piece.equals("\u2659") && x2 == 0) || (piece.equals("\u265F") && x2 == 7);
        f.castle = type == 4;
        f.check = Chess.IsCheck(Chess.IsWhite(piece) ? -1 : 1, this, board);
        return f;
    }

    public boolean Equals(PieceMove other, BoardState board) {
        if (other == null)
            return false;
        if (y != other.y)
            return false;
        if (x != other.x)
            return false;
        if (y2 != other.y2)
            return false;
        if (x2 != other.x2)
            return false;
        if (!piece.equals(other.piece))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    class MoveFlags {
        public String CapturedPiece = "";
        public boolean promotion = false;
        public boolean castle = false;
        public boolean check = false;
    }
}

class PieceActionListener implements ActionListener {

    int y;
    int x;
    int m;

    public PieceActionListener(int y, int x, int m) {
        this.y = y;
        this.x = x;
        this.m = m;
    }

    public void actionPerformed(ActionEvent e) {
        Chess.Select();
        Chess.SetEnabled(y, x, true);
        Chess.RemoveListeners(y, x);
    }

    public void AddAction(int dy, int dx) {

    }

    public boolean AttacksPiece(String piece, BoardState board) {
        return false;
    }

    public ArrayList<PieceMove> GetAvailableMoves(BoardState board, boolean cap) {
        return new ArrayList<PieceMove>();
    }
}

class PawnActionListener extends PieceActionListener {

    boolean check = false;
    boolean pinned = false;

    public PawnActionListener(int y, int x, int m) {
        super(y, x, m);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (m == -1) {
            // doble
            if (y == 6) {
                AddAction(-2, 0, 5);
            }
            // paso
            if (y == 3) {
                if (x < 7
                        && new PieceMove(1, x + 1, y, x + 1, "\u265F", 1).Equals(Chess.LastMove, Chess.GetBoardState()))
                    AddAction(0, 1, 3);
                else if (x > 0
                        && new PieceMove(1, x - 1, y, x - 1, "\u265F", 1).Equals(Chess.LastMove, Chess.GetBoardState()))
                    AddAction(0, -1, 3);
            }
            if (y == 1) {
                // promotion
                AddAction(-1, 0, 2);
                // promotion cap
                AddAction(-1, 1, 4);
                AddAction(-1, -1, 4);

            } else {
                // normal
                AddAction(-1, 0, 0);
                // captura
                AddAction(-1, 1, 1);
                AddAction(-1, -1, 1);
            }
        } else {
            // doble
            if (y == 1) {
                AddAction(2, 0, 5);
            }
            // paso
            if (y == 4) {
                if (x < 7
                        && new PieceMove(1, x + 1, y, x + 1, "\u2659", 1).Equals(Chess.LastMove, Chess.GetBoardState()))
                    AddAction(0, 1, 3);
                else if (x > 0
                        && new PieceMove(1, x - 1, y, x - 1, "\u2659", 1).Equals(Chess.LastMove, Chess.GetBoardState()))
                    AddAction(0, -1, 3);
            }
            if (y == 6) {
                // promotion
                AddAction(1, 0, 2);
                // promotion cap
                AddAction(1, 1, 4);
                AddAction(1, -1, 4);

            } else {
                // normal
                AddAction(1, 0, 0);
                // captura
                AddAction(1, 1, 1);
                AddAction(1, -1, 1);
            }
        }
        Chess.SetAction(y, x, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Chess.PrepareTurn();
            }
        });
    }

    public void AddAction(int dy, int dx, int method) {
        if (Chess.IsBlocked(y + dy, x + dx)) {
            if (Chess.IsCapturable(y + dy, x + dx, m)) {
                switch (method) {
                    case 1:
                        if (!Chess.IsCheck(m,
                                new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2659" : "\u265F"), 1),
                                Chess.GetBoardState())) {
                            Chess.SetEnabled(y + dy, x + dx, true);
                            Chess.SetAction(y + dy, x + dx, new Move(y, x, y + dy, x + dx));
                        }
                        break;
                    case 4:
                        if (!Chess.IsCheck(m,
                                new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2659" : "\u265F"), 3),
                                Chess.GetBoardState())) {
                            Chess.SetEnabled(y + dy, x + dx, true);
                            Chess.SetAction(y + dy, x + dx, new Promote(y, x, y + dy, x + dx, m));
                        }
                        break;
                    case 3:
                        if (!Chess.IsCheck(m,
                                new PieceMove(y, x, y + m, x + dx, (m == -1 ? "\u2659" : "\u265F"), 2),
                                Chess.GetBoardState())) {
                            Chess.SetEnabled(y + m, x + dx, true);
                            Chess.SetAction(y + m, x + dx, new Pass(y, x, y + m, x + dx));
                        }
                        break;
                }
            }
        } else {
            switch (method) {
                case 0:
                    if (!Chess.IsCheck(m, new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2659" : "\u265F"), 1),
                            Chess.GetBoardState())) {
                        Chess.SetEnabled(y + dy, x + dx, true);
                        Chess.SetAction(y + dy, x + dx, new Move(y, x, y + dy, x + dx));
                    }
                    break;
                case 2:
                    if (!Chess.IsCheck(m, new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2659" : "\u265F"), 3),
                            Chess.GetBoardState())) {
                        Chess.SetEnabled(y + dy, x + dx, true);
                        Chess.SetAction(y + dy, x + dx, new Promote(y, x, y + dy, x + dx, m));
                    }
                    break;
                case 5:
                    if (!Chess.IsBlocked(y + dy - m, x + dx)) {
                        if (!Chess.IsCheck(m, new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2659" : "\u265F"), 1),
                                Chess.GetBoardState())) {
                            Chess.SetEnabled(y + dy, x + dx, true);
                            Chess.SetAction(y + dy, x + dx, new Move(y, x, y + dy, x + dx));
                        }
                        break;
                    }
            }
        }
    }

    public ArrayList<PieceMove> GetActions(int dy, int dx, int method, BoardState board, boolean cap) {
        ArrayList<PieceMove> r = new ArrayList<PieceMove>();
        PieceMove move = new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2659" : "\u265F"), 1);
        if (board.IsBlocked(y + dy, x + dx)) {
            if (board.IsCapturable(y + dy, x + dx, m)) {
                switch (method) {
                    case 1:
                        move.type = 1;
                        if (!check || !Chess.IsCheck(m, move, board)) {
                            r.add(move);
                            check = false;
                        } else {
                            pinned = true;
                        }
                        break;
                    case 4:
                        move.type = 3;
                        if (!check || !Chess.IsCheck(m, move, board)) {
                            r.add(move);
                            check = false;
                        } else {
                            pinned = true;
                        }
                        break;
                    case 3:
                        move.type = 2;
                        if (!check || !Chess.IsCheck(m, move, board)) {
                            r.add(move);
                            check = false;
                        } else {
                            pinned = true;
                        }
                        break;
                }
            }
        } else {
            if (!cap) {
                switch (method) {
                    case 0:
                        move.type = 1;
                        if (!check || !Chess.IsCheck(m, move, board)) {
                            r.add(move);
                            check = false;
                        } else {
                            pinned = true;
                        }
                        break;
                    case 2:
                        move.type = 3;
                        if (!check || !Chess.IsCheck(m, move, board)) {
                            r.add(move);
                            check = false;
                        } else {
                            pinned = true;
                        }
                        break;
                    case 5:
                        move.type = 1;
                        if (!board.IsBlocked(y + dy - m, x + dx)) {
                            if (!check || !Chess.IsCheck(m, move, board)) {
                                r.add(move);
                                check = false;
                            } else {
                                pinned = true;
                            }
                            break;
                        }
                }
            }
        }
        return r;
    }

    @Override
    public boolean AttacksPiece(String piece, BoardState board) {
        if (board.IsBlocked(y + m, x + 1)) {
            if (board.IsCapturable(y + m, x + 1, m)) {
                if (board.GetText(y + m, x + 1).equals(piece))
                    return true;
            }
        }
        if (board.IsBlocked(y + m, x - 1)) {
            if (board.IsCapturable(y + m, x - 1, m)) {
                if (board.GetText(y + m, x - 1).equals(piece))
                    return true;
            }
        }
        if (m == -1) {
            if (y == 3) {
                if (board.IsBlocked(y, x + 1)) {
                    if (board.IsCapturable(y, x + 1, m)) {
                        if (board.GetText(y + m, x + 1).equals(piece))
                            return true;
                    }
                }
                if (board.IsBlocked(y, x - 1)) {
                    if (board.IsCapturable(y, x - 1, m)) {
                        if (board.GetText(y + m, x - 1).equals(piece))
                            return true;
                    }
                }
            }
        } else {
            if (y == 4) {
                if (board.IsBlocked(y, x + 1)) {
                    if (board.IsCapturable(y, x + 1, m)) {
                        if (board.GetText(y + m, x + 1).equals(piece))
                            return true;
                    }
                }
                if (board.IsBlocked(y, x - 1)) {
                    if (board.IsCapturable(y, x - 1, m)) {
                        if (board.GetText(y + m, x - 1).equals(piece))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<PieceMove> GetAvailableMoves(BoardState board, boolean cap) {
        ArrayList<PieceMove> r = new ArrayList<PieceMove>();
        check = true;
        pinned = false;
        if (m == -1) {
            if (!pinned && y == 6) {
                r.addAll(GetActions(-2, 0, 5, board, cap));
            }
            if (!pinned && y == 3) {
                if (x < 7 && new PieceMove(1, x + 1, y, x + 1, "\u265F", 1).Equals(board.LastMove, board))
                    r.addAll(GetActions(0, 1, 3, board, cap));
                if (x > 0 && new PieceMove(1, x - 1, y, x - 1, "\u265F", 1).Equals(board.LastMove, board))
                    r.addAll(GetActions(0, -1, 3, board, cap));
            }
            if (!pinned && y == 1) {
                r.addAll(GetActions(-1, 0, 2, board, cap));
                r.addAll(GetActions(-1, 1, 4, board, cap));
                r.addAll(GetActions(-1, -1, 4, board, cap));

            } else {
                if (!pinned) {
                    r.addAll(GetActions(-1, 0, 0, board, cap));
                    r.addAll(GetActions(-1, 1, 1, board, cap));
                    r.addAll(GetActions(-1, -1, 1, board, cap));
                }
            }
        } else {
            if (!pinned && y == 1) {
                r.addAll(GetActions(2, 0, 5, board, cap));
            }
            if (!pinned && y == 4) {
                if (x < 7 && new PieceMove(6, x + 1, y, x + 1, "\u2659", 1).Equals(board.LastMove, board))
                    r.addAll(GetActions(0, 1, 3, board, cap));
                if (x > 0 && new PieceMove(6, x - 1, y, x - 1, "\u2659", 1).Equals(board.LastMove, board))
                    r.addAll(GetActions(0, -1, 3, board, cap));
            }
            if (!pinned && y == 6) {
                r.addAll(GetActions(1, 0, 2, board, cap));
                r.addAll(GetActions(1, 1, 4, board, cap));
                r.addAll(GetActions(1, -1, 4, board, cap));

            } else {
                if (!pinned) {
                    r.addAll(GetActions(1, 0, 0, board, cap));
                    r.addAll(GetActions(1, 1, 1, board, cap));
                    r.addAll(GetActions(1, -1, 1, board, cap));
                }
            }
        }
        return r;
    }
}

class RookActionListener extends PieceActionListener {

    boolean check = false;
    boolean pinned = false;

    public RookActionListener(int y, int x, int m) {
        super(y, x, m);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        AddAction(0, 1);
        AddAction(0, -1);
        AddAction(1, -0);
        AddAction(-1, 0);
        Chess.SetAction(y, x, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Chess.PrepareTurn();
            }
        });
    }

    @Override
    public void AddAction(int dy, int dx) {
        while (true) {
            if (Chess.IsBlocked(y + dy, x + dx)) {
                if (Chess.IsCapturable(y + dy, x + dx, m)) {
                    if (!Chess.IsCheck(m, new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2656" : "\u265C"), 1),
                            Chess.GetBoardState())) {
                        Chess.SetEnabled(y + dy, x + dx, true);
                        Chess.SetAction(y + dy, x + dx, new Move(y, x, y + dy, x + dx));
                    }
                }
                break;
            } else {
                if (!Chess.IsCheck(m, new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2656" : "\u265C"), 1),
                        Chess.GetBoardState())) {
                    Chess.SetEnabled(y + dy, x + dx, true);
                    Chess.SetAction(y + dy, x + dx, new Move(y, x, y + dy, x + dx));
                }
            }
            if (dy < 0) {
                dy--;
            } else if (dy > 0) {
                dy++;
            }
            if (dx < 0) {
                dx--;
            } else if (dx > 0) {
                dx++;
            }
        }
    }

    public ArrayList<PieceMove> GetActions(int dy, int dx, BoardState board, boolean cap) {
        ArrayList<PieceMove> r = new ArrayList<PieceMove>();
        while (true) {
            PieceMove move = new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2656" : "\u265C"), 1);
            if (board.IsBlocked(y + dy, x + dx)) {
                if (board.IsCapturable(y + dy, x + dx, m)) {
                    if (!check || !Chess.IsCheck(m, move, board)) {
                        r.add(move);
                        check = false;
                    } else {
                        pinned = true;
                        break;
                    }
                }
                break;
            } else {
                if (!check || !Chess.IsCheck(m, move, board)) {
                    if (!cap) {
                        r.add(move);
                    }
                    check = false;
                } else {
                    pinned = true;
                    break;
                }
            }
            if (dy < 0) {
                dy--;
            } else if (dy > 0) {
                dy++;
            }
            if (dx < 0) {
                dx--;
            } else if (dx > 0) {
                dx++;
            }
        }
        return r;
    }

    @Override
    public boolean AttacksPiece(String piece, BoardState board) {
        if (AttcksPiecePart(piece, 1, 0, board))
            return true;
        if (AttcksPiecePart(piece, -1, 0, board))
            return true;
        if (AttcksPiecePart(piece, 0, 1, board))
            return true;
        if (AttcksPiecePart(piece, 0, -1, board))
            return true;
        return false;

    }

    private boolean AttcksPiecePart(String piece, int dy, int dx, BoardState board) {
        while (true) {
            if (board.IsBlocked(y + dy, x + dx)) {
                if (board.IsCapturable(y + dy, x + dx, m)) {
                    if (board.GetText(y + dy, x + dx).equals(piece))
                        return true;
                }
                break;
            }
            if (dy < 0) {
                dy--;
            } else if (dy > 0) {
                dy++;
            }
            if (dx < 0) {
                dx--;
            } else if (dx > 0) {
                dx++;
            }
        }
        return false;
    }

    @Override
    public ArrayList<PieceMove> GetAvailableMoves(BoardState board, boolean cap) {
        ArrayList<PieceMove> r = new ArrayList<PieceMove>();
        check = true;
        pinned = false;
        r.addAll(GetActions(0, 1, board, cap));
        if (!pinned)
            r.addAll(GetActions(0, -1, board, cap));
        if (!pinned)
            r.addAll(GetActions(1, -0, board, cap));
        if (!pinned)
            r.addAll(GetActions(-1, 0, board, cap));
        return r;
    }
}

class BishopActionListener extends PieceActionListener {

    boolean check = false;
    boolean pinned = false;

    public BishopActionListener(int y, int x, int m) {
        super(y, x, m);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        AddAction(1, 1);
        AddAction(-1, 1);
        AddAction(1, -1);
        AddAction(-1, -1);
        Chess.SetAction(y, x, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Chess.PrepareTurn();
            }
        });
    }

    @Override
    public void AddAction(int dy, int dx) {
        while (true) {
            if (Chess.IsBlocked(y + dy, x + dx)) {
                if (Chess.IsCapturable(y + dy, x + dx, m)) {
                    if (!Chess.IsCheck(m, new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2657" : "\u265D"), 1),
                            Chess.GetBoardState())) {
                        Chess.SetEnabled(y + dy, x + dx, true);
                        Chess.SetAction(y + dy, x + dx, new Move(y, x, y + dy, x + dx));
                    }
                }
                break;
            } else {
                if (!Chess.IsCheck(m, new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2657" : "\u265A"), 1),
                        Chess.GetBoardState())) {
                    Chess.SetEnabled(y + dy, x + dx, true);
                    Chess.SetAction(y + dy, x + dx, new Move(y, x, y + dy, x + dx));
                }
            }
            if (dy < 0) {
                dy--;
            } else {
                dy++;
            }
            if (dx < 0) {
                dx--;
            } else {
                dx++;
            }
        }
    }

    public ArrayList<PieceMove> GetActions(int dy, int dx, BoardState board, boolean cap) {
        ArrayList<PieceMove> r = new ArrayList<PieceMove>();
        while (true) {
            PieceMove move = new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2657" : "\u265D"), 1);
            if (board.IsBlocked(y + dy, x + dx)) {
                if (board.IsCapturable(y + dy, x + dx, m)) {
                    if (!check || !Chess.IsCheck(m, move, board)) {
                        r.add(move);
                        check = false;
                    } else {
                        pinned = true;
                        break;
                    }
                }
                break;
            } else {
                if (!check || !Chess.IsCheck(m, move, board)) {
                    if (!cap) {
                        r.add(move);
                    }
                    check = false;
                } else {
                    pinned = true;
                    break;
                }
            }
            if (dy < 0) {
                dy--;
            } else if (dy > 0) {
                dy++;
            }
            if (dx < 0) {
                dx--;
            } else if (dx > 0) {
                dx++;
            }
        }
        return r;
    }

    @Override
    public boolean AttacksPiece(String piece, BoardState board) {
        if (AttcksPiecePart(piece, 1, 1, board))
            return true;
        if (AttcksPiecePart(piece, -1, 1, board))
            return true;
        if (AttcksPiecePart(piece, 1, -1, board))
            return true;
        if (AttcksPiecePart(piece, -1, -1, board))
            return true;
        return false;

    }

    private boolean AttcksPiecePart(String piece, int dy, int dx, BoardState board) {
        while (true) {
            if (board.IsBlocked(y + dy, x + dx)) {
                if (board.IsCapturable(y + dy, x + dx, m)) {
                    if (board.GetText(y + dy, x + dx).equals(piece)) {
                        return true;
                    }
                }
                break;
            }
            if (dy < 0) {
                dy--;
            } else if (dy > 0) {
                dy++;
            }
            if (dx < 0) {
                dx--;
            } else if (dx > 0) {
                dx++;
            }
        }
        return false;
    }

    @Override
    public ArrayList<PieceMove> GetAvailableMoves(BoardState board, boolean cap) {
        ArrayList<PieceMove> r = new ArrayList<PieceMove>();
        check = true;
        pinned = false;
        r.addAll(GetActions(1, 1, board, cap));
        if (!pinned)
            r.addAll(GetActions(1, -1, board, cap));
        if (!pinned)
            r.addAll(GetActions(-1, -1, board, cap));
        if (!pinned)
            r.addAll(GetActions(-1, 1, board, cap));
        return r;
    }
}

class QueenActionListener extends PieceActionListener {

    boolean check = false;
    boolean pinned = false;

    public QueenActionListener(int y, int x, int m) {
        super(y, x, m);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        AddAction(0, 1);
        AddAction(0, -1);
        AddAction(1, -0);
        AddAction(-1, 0);
        AddAction(1, 1);
        AddAction(-1, 1);
        AddAction(1, -1);
        AddAction(-1, -1);
        Chess.SetAction(y, x, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Chess.PrepareTurn();
            }
        });
    }

    @Override
    public void AddAction(int dy, int dx) {
        while (true) {
            if (Chess.IsBlocked(y + dy, x + dx)) {
                if (Chess.IsCapturable(y + dy, x + dx, m)) {
                    if (!Chess.IsCheck(m, new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2655" : "\u265B"), 1),
                            Chess.GetBoardState())) {
                        Chess.SetEnabled(y + dy, x + dx, true);
                        Chess.SetAction(y + dy, x + dx, new Move(y, x, y + dy, x + dx));
                    }
                }
                break;
            } else {
                if (!Chess.IsCheck(m, new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u26555" : "\u265B"), 1),
                        Chess.GetBoardState())) {
                    Chess.SetEnabled(y + dy, x + dx, true);
                    Chess.SetAction(y + dy, x + dx, new Move(y, x, y + dy, x + dx));
                }
            }
            if (dy < 0) {
                dy--;
            } else if (dy > 0) {
                dy++;
            }
            if (dx < 0) {
                dx--;
            } else if (dx > 0) {
                dx++;
            }
        }
    }

    public ArrayList<PieceMove> GetActions(int dy, int dx, BoardState board, boolean cap) {
        ArrayList<PieceMove> r = new ArrayList<PieceMove>();
        while (true) {
            PieceMove move = new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2655" : "\u265B"), 1);
            if (board.IsBlocked(y + dy, x + dx)) {
                if (board.IsCapturable(y + dy, x + dx, m)) {
                    if (!check || !Chess.IsCheck(m, move, board)) {
                        r.add(move);
                        check = false;
                    } else {
                        pinned = true;
                        break;
                    }
                }
                break;
            } else {
                if (!check || !Chess.IsCheck(m, move, board)) {
                    if (!cap) {
                        r.add(move);
                    }
                    check = false;
                } else {
                    pinned = true;
                    break;
                }
            }
            if (dy < 0) {
                dy--;
            } else if (dy > 0) {
                dy++;
            }
            if (dx < 0) {
                dx--;
            } else if (dx > 0) {
                dx++;
            }
        }
        return r;
    }

    @Override
    public boolean AttacksPiece(String piece, BoardState board) {
        // System.out.println(board.toString());
        // System.out.println(x);
        // System.out.println(y);
        // System.out.println(m);
        if (AttcksPiecePart(piece, 1, 0, board))
            return true;
        if (AttcksPiecePart(piece, -1, 0, board))
            return true;
        if (AttcksPiecePart(piece, 0, 1, board))
            return true;
        if (AttcksPiecePart(piece, -0, -1, board))
            return true;
        if (AttcksPiecePart(piece, 1, 1, board))
            return true;
        if (AttcksPiecePart(piece, -1, 1, board))
            return true;
        if (AttcksPiecePart(piece, 1, -1, board))
            return true;
        if (AttcksPiecePart(piece, -1, -1, board))
            return true;
        return false;

    }

    private boolean AttcksPiecePart(String piece, int dy, int dx, BoardState board) {
        while (true) {
            // System.out.println("x:" + (x + dx));
            // System.out.println("y:" + (y + y));
            // System.out.println(board.IsBlocked(y + dy, x + dx));
            // System.out.println(board.IsCapturable(y + dy, x + dx, m));
            // System.out.println(board.IsBlocked(y + dy, x + dx));
            if (board.IsBlocked(y + dy, x + dx)) {
                if (board.IsCapturable(y + dy, x + dx, m)) {
                    if (board.GetText(y + dy, x + dx).equals(piece))
                        return true;
                }
                break;
            }
            if (dy < 0) {
                dy--;
            } else if (dy > 0) {
                dy++;
            }
            if (dx < 0) {
                dx--;
            } else if (dx > 0) {
                dx++;
            }
        }
        return false;
    }

    @Override
    public ArrayList<PieceMove> GetAvailableMoves(BoardState board, boolean cap) {
        ArrayList<PieceMove> r = new ArrayList<PieceMove>();
        check = true;
        pinned = false;
        r.addAll(GetActions(0, 1, board, cap));
        if (!pinned)
            r.addAll(GetActions(0, -1, board, cap));
        if (!pinned)
            r.addAll(GetActions(1, -0, board, cap));
        if (!pinned)
            r.addAll(GetActions(-1, 0, board, cap));
        if (!pinned)
            r.addAll(GetActions(1, 1, board, cap));
        if (!pinned)
            r.addAll(GetActions(1, -1, board, cap));
        if (!pinned)
            r.addAll(GetActions(-1, -1, board, cap));
        if (!pinned)
            r.addAll(GetActions(-1, 1, board, cap));
        return r;
    }
}

class KingActionListener extends PieceActionListener {

    public KingActionListener(int y, int x, int m) {
        super(y, x, m);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        AddAction(0, 1);
        AddAction(0, -1);
        AddAction(1, -0);
        AddAction(-1, 0);
        AddAction(1, 1);
        AddAction(-1, 1);
        AddAction(1, -1);
        AddAction(-1, -1);
        if (!Chess.IsCheck(m, null, Chess.GetBoardState())) {
            if (m == -1) {
                if (!Chess.KingMoved1) {
                    if (!Chess.RightRook1) {
                        if (Chess.GetText(y, x + 1).equals("") && Chess.GetText(y, x + 2).equals("")) {
                            if (!Chess.IsCheck(m, new PieceMove(y, x, 7, 7, "2654", 4), Chess.GetBoardState())) {
                                Chess.SetEnabled(y, x + 2, true);
                                Chess.SetAction(y, x + 2, new Castle(y, x, 7, 7));
                            }
                        }
                    }
                    if (!Chess.LeftRook1) {
                        if (Chess.GetText(y, x - 1).equals("") && Chess.GetText(y, x - 2).equals("")
                                && Chess.GetText(y, x - 3).equals("")) {
                            if (!Chess.IsCheck(m, new PieceMove(y, x, 7, 7, "2654", 4), Chess.GetBoardState())) {
                                Chess.SetEnabled(y, x - 2, true);
                                Chess.SetAction(y, x - 2, new Castle(y, x, 7, 0));
                            }
                        }
                    }
                }
            } else {
                if (!Chess.KingMoved2) {
                    if (!Chess.RightRook2) {
                        if (Chess.GetText(y, x + 1).equals("") && Chess.GetText(y, x + 2).equals("")) {
                            if (!Chess.IsCheck(m, new PieceMove(y, x, 7, 7, "265A", 4), Chess.GetBoardState())) {
                                Chess.SetEnabled(y, x + 2, true);
                                Chess.SetAction(y, x + 2, new Castle(y, x, 0, 7));
                            }
                        }
                    }
                    if (!Chess.LeftRook2) {
                        if (Chess.GetText(y, x - 1).equals("") && Chess.GetText(y, x - 2).equals("")
                                && Chess.GetText(y, x - 3).equals("")) {
                            if (!Chess.IsCheck(m, new PieceMove(y, x, 7, 7, "265A", 4), Chess.GetBoardState())) {
                                Chess.SetEnabled(y, x - 2, true);
                                Chess.SetAction(y, x - 2, new Castle(y, x, 0, 0));
                            }
                        }
                    }
                }
            }
        }

        Chess.SetAction(y, x, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Chess.PrepareTurn();
            }
        });
    }

    @Override
    public void AddAction(int dy, int dx) {
        if (Chess.IsBlocked(y + dy, x + dx)) {
            if (Chess.IsCapturable(y + dy, x + dx, m)) {
                if (!Chess.IsCheck(m, new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2654" : "\u265A"), 1),
                        Chess.GetBoardState())) {
                    Chess.SetEnabled(y + dy, x + dx, true);
                    Chess.SetAction(y + dy, x + dx, new Move(y, x, y + dy, x + dx));
                }
            }
        } else {
            if (!Chess.IsCheck(m, new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2654" : "\u265A"), 1),
                    Chess.GetBoardState())) {
                Chess.SetEnabled(y + dy, x + dx, true);
                Chess.SetAction(y + dy, x + dx, new Move(y, x, y + dy, x + dx));
            }
        }
    }

    public ArrayList<PieceMove> GetActions(int dy, int dx, BoardState board, boolean cap) {
        ArrayList<PieceMove> r = new ArrayList<PieceMove>();
        PieceMove move = new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2654" : "\u265A"), 1);
        if (board.IsBlocked(y + dy, x + dx)) {
            if (board.IsCapturable(y + dy, x + dx, m)) {
                if (!Chess.IsCheck(m, move, board)) {
                    r.add(move);
                }
            }
        } else {
            if (!Chess.IsCheck(m, move, board)) {
                if (!cap)
                    r.add(move);
            }
        }
        return r;
    }

    @Override
    public boolean AttacksPiece(String piece, BoardState board) {
        if (AttcksPiecePart(piece, 1, 0, board))
            return true;
        if (AttcksPiecePart(piece, -1, 0, board))
            return true;
        if (AttcksPiecePart(piece, 0, 1, board))
            return true;
        if (AttcksPiecePart(piece, -0, -1, board))
            return true;
        if (AttcksPiecePart(piece, 1, 1, board))
            return true;
        if (AttcksPiecePart(piece, -1, 1, board))
            return true;
        if (AttcksPiecePart(piece, 1, -1, board))
            return true;
        if (AttcksPiecePart(piece, -1, -1, board))
            return true;
        return false;

    }

    private boolean AttcksPiecePart(String piece, int dy, int dx, BoardState board) {
        // System.out.println("dy: " + dy + " dx: " + dx);
        if (board.IsBlocked(y + dy, x + dx)) {
            // System.out.println("si1.1");
            if (board.IsCapturable(y + dy, x + dx, m)) {
                // System.out.println("si1.2");
                if (board.GetText(y + dy, x + dx).equals(piece)) {
                    // System.out.println("si1.3");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<PieceMove> GetAvailableMoves(BoardState board, boolean cap) {
        ArrayList<PieceMove> r = new ArrayList<PieceMove>();
        r.addAll(GetActions(0, 1, board, cap));
        r.addAll(GetActions(0, -1, board, cap));
        r.addAll(GetActions(1, -0, board, cap));
        r.addAll(GetActions(-1, 0, board, cap));
        r.addAll(GetActions(1, 1, board, cap));
        r.addAll(GetActions(1, -1, board, cap));
        r.addAll(GetActions(-1, -1, board, cap));
        r.addAll(GetActions(-1, 1, board, cap));
        if (!Chess.IsCheck(m, null, Chess.GetBoardState())) {
            if (m == -1) {
                if (!board.KingMoved1) {
                    if (!board.RightRook1) {
                        if (board.GetText(y, x + 1).equals("") && board.GetText(y, x + 2).equals("")) {
                            if (!Chess.IsCheck(m, new PieceMove(y, x, 7, 7, "2654", 4), Chess.GetBoardState()))
                                r.add(new PieceMove(y, x, 7, 7, "\u2654", 4));
                        }
                    }
                    if (!board.LeftRook1) {
                        if (board.GetText(y, x - 1).equals("") && board.GetText(y, x - 2).equals("")
                                && board.GetText(y, x - 3).equals("")) {
                            if (!Chess.IsCheck(m, new PieceMove(y, x, 7, 7, "2654", 4), Chess.GetBoardState()))
                                r.add(new PieceMove(y, x, 7, 0, "\u2654", 4));
                        }
                    }
                }
            } else {
                if (!board.KingMoved2) {
                    if (!board.RightRook2) {
                        if (board.GetText(y, x + 1).equals("") && board.GetText(y, x + 2).equals("")) {
                            if (!Chess.IsCheck(m, new PieceMove(y, x, 7, 7, "265A", 4), Chess.GetBoardState()))
                                r.add(new PieceMove(y, x, 0, 7, "\u265A", 4));
                        }
                    }
                    if (!board.LeftRook2) {
                        if (board.GetText(y, x - 1).equals("") && board.GetText(y, x - 2).equals("")
                                && board.GetText(y, x - 3).equals("")) {
                            if (!Chess.IsCheck(m, new PieceMove(y, x, 7, 7, "265A", 4), Chess.GetBoardState()))
                                r.add(new PieceMove(y, x, 0, 0, "\u265A", 4));
                        }
                    }
                }
            }
        }
        return r;
    }
}

class KnightActionListener extends PieceActionListener {

    boolean check = false;
    boolean pinned = false;

    public KnightActionListener(int y, int x, int m) {
        super(y, x, m);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        AddAction(2, 1);
        AddAction(2, -1);
        AddAction(-2, -1);
        AddAction(-2, 1);
        AddAction(1, 2);
        AddAction(-1, 2);
        AddAction(1, -2);
        AddAction(-1, -2);
        Chess.SetAction(y, x, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Chess.PrepareTurn();
            }
        });
    }

    @Override
    public void AddAction(int dy, int dx) {
        if (Chess.IsBlocked(y + dy, x + dx)) {
            if (Chess.IsCapturable(y + dy, x + dx, m)) {
                if (!Chess.IsCheck(m, new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2658" : "\u265E"), 1),
                        Chess.GetBoardState())) {
                    Chess.SetEnabled(y + dy, x + dx, true);
                    Chess.SetAction(y + dy, x + dx, new Move(y, x, y + dy, x + dx));
                }
            }
        } else {
            if (!Chess.IsCheck(m, new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2658" : "\u265E"), 1),
                    Chess.GetBoardState())) {
                Chess.SetEnabled(y + dy, x + dx, true);
                Chess.SetAction(y + dy, x + dx, new Move(y, x, y + dy, x + dx));
            }
        }
    }

    public ArrayList<PieceMove> GetActions(int dy, int dx, BoardState board, boolean cap) {
        ArrayList<PieceMove> r = new ArrayList<PieceMove>();
        PieceMove move = new PieceMove(y, x, y + dy, x + dx, (m == -1 ? "\u2658" : "\u265E"), 1);
        if (board.IsBlocked(y + dy, x + dx)) {
            if (board.IsCapturable(y + dy, x + dx, m)) {
                if (!check || !Chess.IsCheck(m, move, board)) {
                    r.add(move);
                    check = false;
                } else {
                    pinned = true;
                    return r;
                }
            }
        } else {
            if (!check || !Chess.IsCheck(m, move, board)) {
                if (!cap)
                    r.add(move);
                check = false;
            } else {
                pinned = true;
                return r;
            }
        }
        return r;
    }

    @Override
    public boolean AttacksPiece(String piece, BoardState board) {
        if (AttcksPiecePart(piece, 2, 1, board))
            return true;
        if (AttcksPiecePart(piece, 2, -1, board))
            return true;
        if (AttcksPiecePart(piece, -2, 1, board))
            return true;
        if (AttcksPiecePart(piece, -2, -1, board))
            return true;
        if (AttcksPiecePart(piece, 1, 2, board))
            return true;
        if (AttcksPiecePart(piece, -1, 2, board))
            return true;
        if (AttcksPiecePart(piece, 1, -2, board))
            return true;
        if (AttcksPiecePart(piece, -1, -2, board))
            return true;
        return false;

    }

    private boolean AttcksPiecePart(String piece, int dy, int dx, BoardState board) {
        if (board.IsBlocked(y + dy, x + dx)) {
            if (board.IsCapturable(y + dy, x + dx, m)) {
                if (board.GetText(y + dy, x + dx).equals(piece))
                    return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<PieceMove> GetAvailableMoves(BoardState board, boolean cap) {
        ArrayList<PieceMove> r = new ArrayList<PieceMove>();
        pinned = false;
        check = true;
        if (!pinned)
            r.addAll(GetActions(2, 1, board, cap));
        if (!pinned)
            r.addAll(GetActions(2, -1, board, cap));
        if (!pinned)
            r.addAll(GetActions(-2, 1, board, cap));
        if (!pinned)
            r.addAll(GetActions(-2, -1, board, cap));
        if (!pinned)
            r.addAll(GetActions(-1, 2, board, cap));
        if (!pinned)
            r.addAll(GetActions(1, 2, board, cap));
        if (!pinned)
            r.addAll(GetActions(-1, -2, board, cap));
        if (!pinned)
            r.addAll(GetActions(1, -2, board, cap));
        return r;
    }
}

class Move implements ActionListener {

    int y;
    int x;
    int y2;
    int x2;

    public Move(int y, int x, int y2, int x2) {
        this.y = y;
        this.x = x;
        this.y2 = y2;
        this.x2 = x2;
    }

    public void actionPerformed(ActionEvent e) {
        Chess.SetEnabled(y, x, false);
        String piece = Chess.GetText(y, x);
        Chess.SetText(y, x, "");
        Chess.SetText(y2, x2, piece);
        Chess.PutMoveColor(x, y, x2, y2);
        Chess.LastMove = new PieceMove(y, x, y2, x2, piece, y);
        Chess.EndTurn();
    }
}

class Pass implements ActionListener {

    int y;
    int x;
    int y2;
    int x2;

    public Pass(int y, int x, int y2, int x2) {
        this.y = y;
        this.x = x;
        this.y2 = y2;
        this.x2 = x2;
    }

    public void actionPerformed(ActionEvent e) {
        Chess.SetEnabled(y, x, false);
        String piece = Chess.GetText(y, x);
        Chess.SetText(y, x, "");
        Chess.SetText(y, x2, "");
        Chess.SetText(y2, x2, piece);
        Chess.PutMoveColor(x, y, x2, y2);
        Chess.LastMove = new PieceMove(y, x, y2, x2, piece, y);
        Chess.EndTurn();
    }
}

class Promote implements ActionListener {

    int y;
    int x;
    int y2;
    int x2;
    int m;

    public Promote(int y, int x, int y2, int x2, int m) {
        this.y = y;
        this.x = x;
        this.y2 = y2;
        this.x2 = x2;
        this.m = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Chess.SetEnabled(y, x, false);
        Chess.SetText(y, x, "");
        if (m == -1) {
            Chess.SetText(y2, x2, "\u2655");
        } else {
            Chess.SetText(y2, x2, "\u265B");
        }
        Chess.PutMoveColor(x, y, x2, y2);
        Chess.LastMove = new PieceMove(y, x, y2, x2, m == -1 ? "\u2659" : "\u265F", y);
        Chess.EndTurn();
    }
}

class Castle implements ActionListener {
    int y;
    int x;
    int y2;
    int x2;

    public Castle(int y, int x, int y2, int x2) {
        this.y = y;
        this.x = x;
        this.y2 = y2;
        this.x2 = x2;
    }

    public void actionPerformed(ActionEvent e) {
        Chess.SetEnabled(y, x, false);
        String piece = Chess.GetText(y, x);
        String piece2 = Chess.GetText(y2, x2);
        Chess.SetText(y, x + ((x2 > 3) ? 1 : -1), piece2);
        Chess.SetText(y2, x2 + ((x2 > 3) ? -1 : 2), piece);
        Chess.SetText(y, x, "");
        Chess.SetText(y2, x2, "");
        Chess.PutMoveColor(x, y, x2 + ((x2 > 3) ? -1 : 1), y2);
        Chess.LastMove = new PieceMove(y, x, y2, x2 + ((x2 > 3) ? -1 : 1), piece, y);
        Chess.EndTurn();
    }
}

public class Chess {
    public static long[][][][] zobristTable;
    public static HashMap<Integer, Float> TranspositionTable = new HashMap<Integer, Float>();
    public static JButton[][] buttons = new JButton[8][8];
    public static boolean turn;
    public static int turnNum;
    public static boolean KingMoved1 = false;
    public static boolean LeftRook1 = false;
    public static boolean RightRook1 = false;
    public static boolean KingMoved2 = false;
    public static boolean LeftRook2 = false;
    public static boolean RightRook2 = false;
    public static JFrame frame = null;
    public static boolean CPU1 = false;
    public static boolean CPU2 = false;
    public static int CPUDeepness = 1;
    public static int CPUAnnalyzed = 0;
    public static int CPUType1 = 4;
    public static int CPUType2 = 4;
    public static Evalueator[] evalueators = { new PiecesValue(), new EndGameDifference(), new Movility(),
            new Center(), new PieceMaps() };
    public static PieceMove BestCPUMove = null;
    public static PieceMove LastMove = null;
    public static ArrayList<PieceMove> RepitedMoves = new ArrayList<PieceMove>();

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setLayout(new BoxLayout(frame.getContentPane(), 1));
        JPanel GamePanel = new JPanel(new GridLayout(8, 8));
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(buttons[i][j].getFont().deriveFont((40f)));
                buttons[i][j].setEnabled(false);
                GamePanel.add(buttons[i][j]);
            }
        }
        frame.add(GamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(3);
        frame.setSize(700, 700);
        frame.setResizable(false);
        frame.setVisible(true);
        StartGame();
    }

    public static void StartGame() {
        PutMoveColor(-1, -1, -1, -1);
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                SetText(i, j, "");
            }
        }
        if (zobristTable == null) {
            zobristTable = new long[8][8][12][2];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 12; k++) {
                        for (int l = 0; l < 2; l++) {
                            zobristTable[i][j][k][l] = new Random().nextLong();
                        }
                    }
                }
            }
        }
        SetText(0, 0, "\u265C");
        SetText(0, 1, "\u265E");
        SetText(0, 2, "\u265D");
        SetText(0, 3, "\u265B");
        SetText(0, 4, "\u265A");
        SetText(0, 5, "\u265D");
        SetText(0, 6, "\u265E");
        SetText(0, 7, "\u265C");
        for (int i = 0; i < 8; i++) {
            SetText(1, i, "\u265F");
        }
        SetText(7, 0, "\u2656");
        SetText(7, 1, "\u2658");
        SetText(7, 2, "\u2657");
        SetText(7, 3, "\u2655");
        SetText(7, 4, "\u2654");
        SetText(7, 5, "\u2657");
        SetText(7, 6, "\u2658");
        SetText(7, 7, "\u2656");
        for (int i = 0; i < 8; i++) {
            SetText(6, i, "\u2659");
        }
        turn = true;
        turnNum = 0;
        KingMoved1 = false;
        KingMoved2 = false;
        RightRook1 = false;
        RightRook2 = false;
        LeftRook1 = false;
        LeftRook2 = false;
        PrepareTurn();
    }

    public static void PrepareTurn() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                RemoveListeners(i, j);
                SetEnabled(i, j, false);
            }
        }
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (IsAllay(i, j, turn)) {
                    SetEnabled(i, j, true);
                    SetAction(i, j, GetPieceAction(GetText(i, j), i, j, turn ? -1 : 1));
                }
            }
        }
        if (CPU1 && turn) {
            CPUMove(-1);
        }
        if (CPU2 && !turn) {
            CPUMove(1);
        }

    }

    public static void EndTurn() {
        if (turn) {
            if (!GetText(7, 4).equals("\u2654"))
                KingMoved1 = true;
            if (!GetText(7, 0).equals("\u2656"))
                LeftRook1 = true;
            if (!GetText(7, 7).equals("\u2656"))
                RightRook1 = true;
        } else {
            if (!GetText(0, 4).equals("\u265A"))
                KingMoved2 = true;
            if (!GetText(0, 0).equals("\u265C"))
                LeftRook2 = true;
            if (!GetText(0, 7).equals("\u265C"))
                RightRook2 = true;

        }
        turn = !turn;
        if (IsCheckMate(turn ? -1 : 1, null)) {
            if (IsCheck(turn ? -1 : 1, null, GetBoardState())) {
                DisplayCheckmate(false);

            } else {
                DisplayCheckmate(true);
            }
            Select();
            return;
        }
        turnNum++;
        PrepareTurn();
    }

    public static void SetEnabled(int y, int x, boolean b) {
        // System.out.println(b);
        buttons[y][x].setEnabled(b);
    }

    public static boolean GetEnabled(int y, int x) {
        return buttons[y][x].isEnabled();
    }

    public static void SetText(int y, int x, String s) {
        buttons[y][x].setText(s);
    }

    public static String GetText(int y, int x) {
        if (y < 0 || y > 7 || x < 0 || x > 7)
            return "B";
        return buttons[y][x].getText();
    }

    public static void SetAction(int y, int x, ActionListener lis) {
        buttons[y][x].addActionListener(lis);
    }

    public static void RemoveListeners(int y, int x) {
        for (ActionListener lis : buttons[y][x].getActionListeners()) {
            buttons[y][x].removeActionListener(lis);
        }
    }

    public static boolean IsWhite(String s) {
        return (s.equals("\u2654") || s.equals("\u2655") || s.equals("\u2656") || s.equals("\u2657")
                || s.equals("\u2658") || s.equals("\u2659"));
    }

    public static Boolean IsBlocked(int y, int x) {
        String s = GetText(y, x);
        if (s == "")
            return false;
        else
            return true;
    }

    public static Boolean IsCapturable(int y, int x, int m) {
        String s = GetText(y, x);
        if (s == "")
            return false;
        else if (s == "B")
            return false;
        else {
            if (m == -1)
                return !IsWhite(s);
            else
                return IsWhite(s);
        }
    }

    public static PieceActionListener GetPieceAction(String s, int y, int x, int m) {
        switch (s) {
            case "\u2656":
            case "\u265C":
                return new RookActionListener(y, x, m);
            case "\u2658":
            case "\u265E":
                return new KnightActionListener(y, x, m);
            case "\u2657":
            case "\u265D":
                return new BishopActionListener(y, x, m);
            case "\u2655":
            case "\u265B":
                return new QueenActionListener(y, x, m);
            case "\u2654":
            case "\u265A":
                return new KingActionListener(y, x, m);
            case "\u2659":
            case "\u265F":
                return new PawnActionListener(y, x, m);
            default:
                return new PieceActionListener(y, x, m);
        }
    }

    public static void Select() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                SetEnabled(i, j, false);
            }
        }
    }

    public static boolean IsAllay(int y, int x, boolean t) {
        if (t) {
            return GetText(y, x) != "" && IsWhite(GetText(y, x));
        } else {
            return GetText(y, x) != "" && !IsWhite(GetText(y, x));
        }
    }

    // returns true if there is a check after the movement move in the Board board
    public static boolean IsCheck(int m, PieceMove move, BoardState board) {
        // System.out.println("IsCheck");
        String king = (m == -1 ? "\u2654" : "\u265A");
        BoardState b = board.DoMove(move);
        ArrayList<PieceActionListener> pieces = b.GetPieces(-m);
        for (PieceActionListener piece : pieces) {
            if (piece.AttacksPiece(king, b)) {
                // System.out.println("csi");
                // System.out.println(piece.getClass());
                // System.out.println(piece.x);
                // System.out.println(piece.y);
                // System.out.println(piece.m);
                return true;
            }
        }
        // System.out.println("cno");
        return false;
    }

    public static boolean IsCheckMate(int m, BoardState board) {
        // System.out.println("Checking for Mate");
        if (board == null)
            board = GetBoardState();
        ArrayList<PieceActionListener> pieces = board.GetPieces(m);
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).GetAvailableMoves(board, false).size() > 0) {
                return false;
            }
        }
        // System.out.println("si");
        return true;
    }

    public static void DisplayCheckmate(Boolean draw) {
        JFrame checkMatFrame = new JFrame();
        checkMatFrame.setUndecorated(true);
        checkMatFrame.setLayout(new BoxLayout(checkMatFrame.getContentPane(), 1));
        JLabel MateLabel;
        if (draw)
            MateLabel = new JLabel("DRAW");
        else {
            MateLabel = new JLabel("CHECKMATE" + (turn ? " BLACK WINS" : " WHITE WINS"));
        }
        JButton Reset = new JButton("Play Again");
        JPanel ResetPanel = new JPanel();
        JPanel LabelPanel = new JPanel();
        Reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.pack();
                frame.setDefaultCloseOperation(3);
                frame.setSize(800, 800);
                frame.setResizable(false);
                frame.setVisible(true);
                StartGame();
                checkMatFrame.dispose();
            }
        });
        frame.addComponentListener(new ComponentAdapter() {
            public void componentMoved(ComponentEvent e) {
                checkMatFrame.setLocationRelativeTo(frame);
            }
        });
        LabelPanel.add(MateLabel);
        ResetPanel.add(Reset);
        checkMatFrame.add(LabelPanel);
        checkMatFrame.add(ResetPanel);
        checkMatFrame.pack();
        checkMatFrame.setSize(200, 100);
        checkMatFrame.setAlwaysOnTop(true);
        checkMatFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true));
        checkMatFrame.setLocationRelativeTo(frame);

        checkMatFrame.setResizable(false);
        checkMatFrame.setVisible(true);
    }

    public static void CPUMove(int m) {
        CPUAnnalyzed = 0;
        long startTime = System.nanoTime();
        CalculateMoves2(CPUDeepness, m, GetBoardState(), Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY); // neg-pos
        long endTime = System.nanoTime();
        System.out.println(CPUAnnalyzed);
        System.out.println((endTime - startTime) / 1000000000.0);
        System.out.println("value: " + EvaluateBoard(GetBoardState().DoMove(BestCPUMove)));
        DoMove(BestCPUMove);
    }

    public static float CalculateMoves2(int deep, int m, BoardState board, float alpha, float beta) {
        if (deep == 0) {
            float a = CalculateCaptures2(board, alpha, beta, m);
            System.out.println(a + " " + m);
            System.out.println(board.toString());
            return a;
        }
        ArrayList<PieceMove> BestMoves = new ArrayList<PieceMove>();
        ArrayList<PieceMove> moves = OrderMoves(GenerateMoves(board, m), board);
        BoardState NextBoard;
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        for (PieceMove move : moves) {
            float value = 0;
            NextBoard = board.DoMove(move);
            if (TranspositionTable.containsKey(NextBoard.toHash())) {
                value = TranspositionTable.get(NextBoard.toHash());
            } else {
                value = -CalculateMoves2(deep - 1, m * -1, NextBoard, -beta, -alpha);
                TranspositionTable.put(NextBoard.toHash(), value);
            }
            if (deep == CPUDeepness) {
                if (value > alpha) {
                    BestMoves.clear();
                    BestMoves.add(move);
                } else if (value == alpha) {
                    BestMoves.add(move);
                }
            }
            CPUAnnalyzed++;
            // Alpha-beta pruning
            if (value >= beta) {
                return beta;
            }
            alpha = Math.max(alpha, value);
        }
        if (deep == CPUDeepness) {
            int randomIndex = new Random().nextInt(BestMoves.size());
            BestCPUMove = BestMoves.get(randomIndex);
            // System.out.println("value: " + BestMovev);
        }
        return alpha;
    }

    public static float CalculateMoves(int deep, int m, BoardState board, float alpha, float beta, int m2) {
        if (deep == 0) {
            return CalculateCaptures(m, board, alpha, beta, m2);
        }
        float BestMovev = -1000;
        ArrayList<PieceMove> BestMoves = new ArrayList<PieceMove>();
        ArrayList<PieceMove> moves = OrderMoves(GenerateMoves(board, m), board);
        BoardState NextBoard;
        for (PieceMove move : moves) {
            float value = 0;
            NextBoard = board.DoMove(move);
            if (TranspositionTable.containsKey(NextBoard.toHash())) {
                value = TranspositionTable.get(NextBoard.toHash());
            } else {
                value = CalculateMoves(deep - 1, m * -1, NextBoard, alpha, beta, m2);
                TranspositionTable.put(NextBoard.toHash(), value);
            }
            if (value > BestMovev) {
                BestMoves.clear();
                BestMoves.add(move);
                BestMovev = value;
            } else if (value == BestMovev) {
                BestMoves.add(move);
            }
            CPUAnnalyzed++;
            // Alpha-beta pruning

            if (m == -1) {
                if (beta < value) {
                    return beta;
                }
                alpha = Math.max(alpha, value);
            } else {
                if (value < alpha) {
                    return alpha;
                }
                beta = Math.min(beta, value);
            }
        }
        if (deep == CPUDeepness) {
            int randomIndex = new Random().nextInt(BestMoves.size());
            BestCPUMove = BestMoves.get(randomIndex);
            // System.out.println("value: " + BestMovev);
        }
        return -BestMovev * m2;
    }

    public static float CalculateMoves(int deep, int m, BoardState board, int m2) {
        if (deep == 0) {
            CPUAnnalyzed++;
            System.out.println(CPUAnnalyzed + "/4,865,609");
            return EvaluateBoard(board);
        }
        float BestMovev = -1000;
        ArrayList<PieceMove> BestMoves = new ArrayList<PieceMove>();
        ArrayList<PieceMove> moves = OrderMoves(GenerateMoves(board, m), board);
        BoardState NextBoard;
        for (PieceMove move : moves) {
            float value = 0;
            NextBoard = board.DoMove(move);
            value = CalculateMoves(deep - 1, m * -1, NextBoard, m2);
            if (value > BestMovev) {
                BestMoves.clear();
                BestMoves.add(move);
                BestMovev = value;
            } else if (value == BestMovev) {
                BestMoves.add(move);
            }
        }
        if (deep == CPUDeepness) {
            int randomIndex = new Random().nextInt(BestMoves.size());
            BestCPUMove = BestMoves.get(randomIndex);
        }
        return -BestMovev * m2;
    }

    public static BoardState GetBoardState() {
        BoardState r = new BoardState();
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                r.SetText(i, j, GetText(i, j));
            }
        }
        r.SetCastle(KingMoved1, LeftRook2, RightRook2, KingMoved2, LeftRook2, RightRook1);
        if (LastMove != null)
            r.LastMove = new PieceMove(LastMove);
        return r;
    }

    public static ArrayList<PieceMove> GenerateMoves(BoardState board, int m) {
        ArrayList<PieceMove> moves = new ArrayList<PieceMove>();
        ArrayList<PieceActionListener> pieces = board.GetPieces(m);
        for (PieceActionListener piece : pieces) {
            moves.addAll(piece.GetAvailableMoves(board, false));
        }
        return moves;
    }

    public static ArrayList<PieceMove> GenerateMoves(BoardState board, int m, boolean cap) {
        ArrayList<PieceMove> moves = new ArrayList<PieceMove>();
        ArrayList<PieceActionListener> pieces = board.GetPieces(m);
        for (PieceActionListener piece : pieces) {
            moves.addAll(piece.GetAvailableMoves(board, cap));
        }
        return moves;
    }

    public static float EvaluateBoard(BoardState board) {
        return evalueators[turn ? CPUType1 : CPUType2].EvaluatePosition(board);
    }

    public static void DoMove(PieceMove move) {
        switch (move.type) {
            case 1:
                String piece = GetText(move.y, move.x);
                SetText(move.y, move.x, "");
                SetText(move.y2, move.x2, piece);
                break;
            case 2:
                piece = GetText(move.y, move.x);
                SetText(move.y, move.x, "");
                SetText(move.y, move.x2, "");
                SetText(move.y2, move.x2, piece);
                break;
            case 3:
                SetText(move.y, move.x, "");
                if (Chess.IsWhite(move.piece)) {
                    SetText(move.y2, move.x2, "\u2655");
                } else {
                    SetText(move.y2, move.x2, "\u265B");
                }
                break;
            case 4:
                piece = GetText(move.y, move.x);
                String piece2 = GetText(move.y2, move.x2);
                SetText(move.y, move.x + ((move.x2 > 3) ? 1 : -1), piece2);
                SetText(move.y2, move.x2 + ((move.x2 > 3) ? -1 : 2), piece);
                SetText(move.y, move.x, "");
                SetText(move.y2, move.x2, "");
                break;
        }
        PutMoveColor(move.x, move.y, move.x2, move.y2);
        LastMove = move;
        EndTurn();
    }

    public static ArrayList<PieceMove> OrderMoves(ArrayList<PieceMove> moves, BoardState board) {
        PieceMove m = null;
        for (int i = 0; i < moves.size(); i++) {
            PieceMove.MoveFlags flags = moves.get(i).GetFlags(board);
            if (flags.promotion) {
                m = moves.get(i);
                moves.remove(i);
                moves.add(0, m);
            }
            if (flags.check) {
                m = moves.get(i);
                moves.remove(i);
                moves.add(0, m);
            }
            if (flags.castle) {
                m = moves.get(i);
                moves.remove(i);
                moves.add(0, m);
            }
            if (GetPieceValue(flags.CapturedPiece) - GetPieceValue(moves.get(i).piece) > 0) {
                m = moves.get(i);
                moves.remove(i);
                moves.add(0, m);
            }
            if (GetPieceValue(flags.CapturedPiece) > 4) {
                m = moves.get(i);
                moves.remove(i);
                moves.add(0, m);
            }
        }
        return moves;
    }

    public static float GetPieceValue(String piece) {
        switch (piece) {
            case "\u2656":
            case "\u265C":
                return 5;
            case "\u2658":
            case "\u265E":
                return 3;
            case "\u2657":
            case "\u265D":
                return 3.5f;
            case "\u2655":
            case "\u265B":
                return 9;
            case "\u2659":
            case "\u265F":
                return 1;
            default:
                return 0;
        }
    }

    public static int GetPieceType(String piece) {
        switch (piece) {
            case "\u2656":
                return 0;
            case "\u265C":
                return 1;
            case "\u2658":
                return 2;
            case "\u265E":
                return 3;
            case "\u2657":
                return 4;
            case "\u265D":
                return 5;
            case "\u2655":
                return 6;
            case "\u265B":
                return 7;
            case "\u2659":
                return 8;
            case "\u265F":
                return 9;
            case "\u2654":
                return 10;
            case "\u265A":
                return 11;
            default:
                return 0;
        }
    }

    public static void PutMoveColor(int x, int y, int x2, int y2) {
        if (LastMove != null) {
            buttons[LastMove.y][LastMove.x].setBackground(null);
            buttons[LastMove.y2][LastMove.x2].setBackground(null);
        }
        if (x == -1)
            return;
        buttons[y][x].setBackground(Color.getHSBColor(0.1666f, 0.3f, 1f));
        buttons[y2][x2].setBackground(Color.getHSBColor(0.1666f, 0.3f, 1f));
    }

    public static float CalculateCaptures(int m, BoardState board, float alpha, float beta, int m2) {
        float value = EvaluateBoard(board);
        if (m == -1) {
            if (beta < value) {
                return beta;
            }
            alpha = Math.max(alpha, value);
        } else {
            if (value < alpha) {
                return alpha;
            }
            beta = Math.min(beta, value);
        }
        float BestMovev = -1000;
        ArrayList<PieceMove> BestMoves = new ArrayList<PieceMove>();
        ArrayList<PieceMove> moves = OrderMoves(GenerateMoves(board, m, true), board);
        BoardState NextBoard;
        for (PieceMove move : moves) {
            NextBoard = board.DoMove(move);
            if (TranspositionTable.containsKey(NextBoard.toHash())) {
                value = TranspositionTable.get(NextBoard.toHash());
            } else {
                value = CalculateCaptures(m * -1, NextBoard, alpha, beta, m2);
                TranspositionTable.put(NextBoard.toHash(), value);
            }
            if (value > BestMovev) {
                BestMoves.clear();
                BestMoves.add(move);
                BestMovev = value;
            } else if (value == BestMovev) {
                BestMoves.add(move);
            }
            CPUAnnalyzed++;
            // Alpha-beta pruning

            if (m == -1) {
                if (beta < value) {
                    return beta;
                }
                alpha = Math.max(alpha, value);
            } else {
                if (value < alpha) {
                    return alpha;
                }
                beta = Math.min(beta, value);
            }
        }
        return -BestMovev * m2;
    }

    public static float CalculateCaptures2(BoardState board, float alpha, float beta, int m) {
        float value = EvaluateBoard(board);
        if (value >= beta) {
            return beta;
        }
        alpha = Math.max(alpha, value);
        ArrayList<PieceMove> moves = GenerateMoves(board, m, true);
        BoardState NextBoard;

        for (PieceMove move : moves) {
            NextBoard = board.DoMove(move);
            if (TranspositionTable.containsKey(NextBoard.toHash())) {
                value = TranspositionTable.get(NextBoard.toHash());
            } else {
                value = -CalculateCaptures2(NextBoard, -beta, -alpha, -m);
                TranspositionTable.put(NextBoard.toHash(), value);
            }
            CPUAnnalyzed++;
            // Alpha-beta pruning
            if (value >= beta) {
                return beta;
            }
            alpha = Math.max(alpha, value);
        }
        return alpha;
    }
}