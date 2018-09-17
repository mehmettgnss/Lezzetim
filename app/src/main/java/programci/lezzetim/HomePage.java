package programci.lezzetim;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.ContextMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;

public class HomePage extends AppCompatActivity {
    SessionManagement sm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sm = new SessionManagement(getApplicationContext());
        sm.checkLogin();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        android.app.FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.action_foodshare) {
            Intent i = new Intent(HomePage.this,sharefood.class);
            startActivity(i);
            return true;
        }
        else if (id == R.id.action_settings){
            Intent i = new Intent(HomePage.this,settings.class);
            startActivity(i);
            return true;
        }
        else if (id == R.id.action_pass){
            Intent i = new Intent(HomePage.this,pass.class);
            startActivity(i);
            return true;
        }
        else if (id == R.id.action_exit){
            Intent i = new Intent(HomePage.this,cikis.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}