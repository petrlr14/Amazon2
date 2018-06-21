package com.pdm2018.amazon2;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.pdm2018.amazon2.Menu.MenuModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expandableListView;
    private List<MenuModel> headerList = new ArrayList<>();
    private HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    private String whislist_menu_title, promo_menu_title, liquidaciones_menu_title, categorias_menu_title, settings_menu_title,logout_menu_title;
    private List<MenuModel> childModelsList;
    private LiveData<List<String>> listLiveData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void prepareMenuData() {

        get_menu_titles();
        MenuModel menuModel = new MenuModel(whislist_menu_title, false, true); //Menu of Java Tutorials
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel(liquidaciones_menu_title, false, true); //Menu of Java Tutorials
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
        menuModel = new MenuModel(promo_menu_title, false, true); //Menu of Java Tutorials
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

//        menuModel = new MenuModel(categorias_menu_title, true, true); //Menu of Java Tutorials
//        headerList.add(menuModel);
//        childModelsList = new ArrayList<>();
//        //todo ESTE LIVEDATA ES PARA OBTENER LAS CATEGORIAS DE LA API PARA LLENAR LOS HIJOS DEL PADRE "categorias" si no se entiende preguntarle a Miguel Aviles
//        listLiveData = viewModel.getCategories();
//        listLiveData.observe(this, new Observer<List<String>>() {
//            @Override
//            public void onChanged(@Nullable List<String> strings) {
//                childModelsList.clear();
//                for (String c : strings) {
//                    MenuModel childModel = new MenuModel(c, false, false);
//                    childModelsList.add(childModel);
//                }
//            }
//        });
//        if (menuModel.hasChildren) {
//            childList.put(menuModel, childModelsList);
//        }
        menuModel = new MenuModel(categorias_menu_title+"", true, true); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("Hombres", false, false);
        childModelsList.add(childModel);
        childModel = new MenuModel("Mujeres", false, false);
        childModelsList.add(childModel);
        childModel = new MenuModel("Niños", false, false);
        childModelsList.add(childModel);
        if (menuModel.hasChildren) {
          childList.put(menuModel, childModelsList);
        }

        menuModel = new MenuModel(settings_menu_title, false, true); //Menu of Java Tutorials
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
        menuModel = new MenuModel(logout_menu_title, false, true); //Menu of Java Tutorials
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
    }

    //Setting adapter for the expandable list and making the visuals appear, + adding what would happen when selected
    private void populateExpandableList() {

        expandableListAdapter = new com.pdm2018.amazon2.Menu.ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        //Click listener for parent option
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                get_menu_titles();
                Fragment fragment;
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        if (headerList.get(groupPosition).menuName.equals(whislist_menu_title)) {
//                            fragment = new News();
//                            transaction.replace(R.id.content, fragment);
//                            transaction.addToBackStack(null);
//                            transaction.commit();
                        }
                        if (headerList.get(groupPosition).menuName.equals(promo_menu_title)) {
//                            fragment = new NewPasword();
//                            transaction.replace(R.id.content, fragment);
//                            transaction.addToBackStack(null);
//                            transaction.commit();
                        }
                        if (headerList.get(groupPosition).menuName.equals(liquidaciones_menu_title)) {
//                            fragment = new NewPasword();
//                            transaction.replace(R.id.content, fragment);
//                            transaction.addToBackStack(null);
//                            transaction.commit();
                        }


                        if (headerList.get(groupPosition).menuName.equals(settings_menu_title)) {

                        }

                        if (headerList.get(groupPosition).menuName.equals(logout_menu_title)) {
//                            SharedPreferences sp = getSharedPreferences(getPackageName(), MODE_PRIVATE);
//                            sp.edit().putString("token", "").apply();
//                            getSupportFragmentManager().popBackStack();
//                            fragment = new Start();
//                            transaction.replace(R.id.drawer_layout, fragment);
//                            transaction.addToBackStack(null);
//                            transaction.commit();
                        }

                        onBackPressed();
                    }
                }
                return false;
            }
        });
        //Click listener for any child option
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Fragment fragment = new Fragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                if (childList.get(headerList.get(groupPosition)) != null) {
//                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
//                    String category = childList.get(headerList.get(groupPosition)).get(childPosition).menuName;
//                    if (listLiveData.getValue().contains(category)) {
//                        fragment = Games.newInstance(category);
//                        transaction.replace(R.id.content, fragment);
//                        transaction.addToBackStack(null);
//                        transaction.commit();
//
//                    }
//                    onBackPressed();
//                }
                if (childList.get(headerList.get(groupPosition)) != null) {
                    if (childList.get(headerList.get(groupPosition)).get(childPosition).menuName.equals("Hombres")) {
                        fragment=new Login();
                        transaction.replace(R.id.container,fragment).commit();
                    }
                    onBackPressed();

                }
                return false;
            }
        });
    }
    public void get_menu_titles() {
        whislist_menu_title ="Wishlist";
        promo_menu_title = "Promociones";
        liquidaciones_menu_title = "Liquidaciones";
        categorias_menu_title = "Categorias";
        settings_menu_title = "Ajustes";
        logout_menu_title="Salir";
    }
}
