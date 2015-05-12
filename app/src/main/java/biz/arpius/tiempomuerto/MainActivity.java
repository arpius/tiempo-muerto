package biz.arpius.tiempomuerto;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vista vista = new Vista(this);
        setContentView(vista);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            mostrarInfo();
        }

        if (id == R.id.action_limpiar) {
            recreate();
        }

        return super.onOptionsItemSelected(item);
    }

    private void mostrarInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.action_info);

        final TextView txt = new TextView(this);
        txt.setText(R.string.autor);

        builder.setView(txt);
        builder.setPositiveButton("Ok", null);
        builder.show();
    }

    class Vista extends View {

        private float x = 50;
        private float y = 50;
        private String accion = "acción";
        private Path ruta = new Path();

        public Vista(Context ctx) {
            super(ctx);
        }

        public void onDraw(Canvas canvas) {
            Paint pintar = new Paint();

            pintar.setStyle(Paint.Style.STROKE);
            pintar.setStrokeWidth(11);
            pintar.setColor(Color.BLUE);

            int ancho = canvas.getWidth();

            if (accion == "down") ruta.moveTo(x, y);
            if (accion == "move") ruta.lineTo(x, y);

            Drawable drawable = getDrawable(R.drawable.cancha_basket);
            drawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
            drawable.draw(canvas);

            invalidate();

            canvas.drawPath(ruta, pintar);
        }

        public boolean onTouchEvent(MotionEvent e) {
            x = e.getX();
            y = e.getY();

            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    accion = "down";
                    break;
                case MotionEvent.ACTION_MOVE:
                    accion = "move";
                    break;
            }

            return true;
        }
    }
}
