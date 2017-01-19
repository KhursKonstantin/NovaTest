package by.novacom.novatest;

import android.app.SearchManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import by.novacom.novatest.rest.ICallBack;
import by.novacom.novatest.rest.ServiceMan;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private TextView toolText;
    private DrawerLayout drawer;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        initToolbar();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ServiceMan.getInstance().getGifs(new ICallBack() {
            @Override
            public void response(boolean isError) {
                if (!isError) {

                    toolText.setText(R.string.tr_gifs);
                    progressBar.setVisibility(progressBar.GONE);
                    setDataToList();
                } else {
                    toolText.setText(R.string.error);
                    progressBar.setVisibility(progressBar.GONE);
                    Toast.makeText(MainActivity.this, getString(R.string.error), Toast.LENGTH_LONG).show();

                }

            }
        });



    }

    private void setDataToList()
    {
        list = new ArrayList<>();
        for (int i = 0; i < ServiceMan.getRoot().getData().size(); i++)
        {
            String a = ServiceMan.getRoot().getData().get(i).getImages().getFixedHeightStill().getUrl();
            list.add(a);
        }

        RecycAdapter adapter = new RecycAdapter(MainActivity.this, list,
                new RecycAdapter.OnLayoutClickListener() {
                    @Override
                    public void onClick(int position, String url) {
                        //Toast.makeText(MainActivity.this, url, Toast.LENGTH_LONG).show();
                        AnimatedActivity.show(MainActivity.this, position);
                    }
                });
        recyclerView.setAdapter(adapter);
    }
    private void initToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolText = (TextView) toolbar.findViewById(R.id.tooltext);
        ActionBar actionBar = getSupportActionBar();
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,  drawer, toolbar,
                R.string.open_drawer, R.string.close_drawer);
        drawer.setDrawerListener(mDrawerToggle);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        mDrawerToggle.syncState();

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.menusearch, menu);

        SearchManager s = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);
        final SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
        search.setSearchableInfo(s.getSearchableInfo(MainActivity.this.getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                search.onActionViewCollapsed();
                progressBar.setVisibility(progressBar.VISIBLE);
                ServiceMan.getInstance().getGifSearch(query, new ICallBack() {
                    @Override
                    public void response(boolean isError) {
                        if (!isError) {

                            progressBar.setVisibility(progressBar.GONE);
                            toolText.setText(query);
                            setDataToList();
                            if (list.size() == 0)
                                Toast.makeText(MainActivity.this, R.string.no_result, Toast.LENGTH_LONG).show();


                        } else {

                            progressBar.setVisibility(progressBar.GONE);
                            Toast.makeText(MainActivity.this, getString(R.string.error), Toast.LENGTH_LONG).show();
                        }

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String rate = null;

        switch (item.getItemId())
        {
            case R.id.nav_home:
            {
                progressBar.setVisibility(progressBar.VISIBLE);
                ServiceMan.getInstance().getGifs(new ICallBack() {
                    @Override
                    public void response(boolean isError) {
                        if (!isError) {

                            toolText.setText(R.string.tr_gifs);
                            progressBar.setVisibility(progressBar.GONE);
                            setDataToList();
                        } else {
                            toolText.setText(R.string.error);
                            progressBar.setVisibility(progressBar.GONE);
                            Toast.makeText(MainActivity.this, getString(R.string.error), Toast.LENGTH_LONG).show();

                        }

                    }
                });
                break;
            }
            case R.id.nav_g:
            {
                rate = "g";
                break;
            }
            case R.id.nav_pg:
            {
                rate = "pg";
                break;
            }
            case R.id.nav_pgiz:
            {
                rate = "pg-13";
                break;
            }
            case R.id.nav_r:
            {
                rate = "r";
                break;
            }

        }

        if(!TextUtils.isEmpty(rate)) {
            progressBar.setVisibility(progressBar.VISIBLE);
            ServiceMan.getInstance().getRating(toolText.getText().toString(), rate, new ICallBack() {
                @Override
                public void response(boolean isError) {
                    if (!isError) {

                        progressBar.setVisibility(progressBar.GONE);
                        setDataToList();
                        if (list.size() == 0)
                            Toast.makeText(MainActivity.this, R.string.no_result, Toast.LENGTH_LONG).show();


                    } else {

                        progressBar.setVisibility(progressBar.GONE);
                        Toast.makeText(MainActivity.this, getString(R.string.error), Toast.LENGTH_LONG).show();
                    }

                }
            });
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
