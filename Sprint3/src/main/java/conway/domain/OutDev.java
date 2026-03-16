package conway.domain;

import io.javalin.websocket.WsMessageContext;

public class OutDev implements IOutDev {

    private WsMessageContext ctx;

    public void setCtx(WsMessageContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void display(String msg) {
        if (ctx != null) ctx.send(msg);
    }

    @Override
    public void displayCell(IGrid grid, int x, int y) {
        if (ctx != null) {
            int color = grid.getCellValue(x, y) ? 0 : 1;
            ctx.send("cell(" + x + "," + y + "," + color + ")");
        }
    }

    @Override
    public void displayGrid(IGrid grid) {
        if (ctx != null) {
            for (int i = 0; i < grid.getRowsNum(); i++) {
                for (int j = 0; j < grid.getColsNum(); j++) {
                    int color = grid.getCellValue(i, j) ? 0 : 1;
                    ctx.send("cell(" + i + "," + j + "," + color + ")");
                }
            }
        }
    }

    @Override
    public void close() {}
}
