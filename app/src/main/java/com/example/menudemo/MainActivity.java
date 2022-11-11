package com.example.menudemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ctx_btn:演示ContextMenu
        //1.注册
        //registerForContextMenu(findViewById(R.id.ctx_btn));
        //2.创建 覆盖
        //3.菜单项的操作 覆盖onContextItemSelected
        //4.为按钮设置上下文操作模式
        //第一小点 实现接口ActionMode CallBack
        //第二小点 在View的长按事件中启动上下文操作模式
        findViewById(R.id.ctx_btn).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActionMode(cb);
                return false;
            }
        });

        //popup_btn;演示PopupMenu
       final Button popupBtn = findViewById(R.id.popup_btn);
       popupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //实例化PopupMenu对象(参数2：被锚定的view)
                PopupMenu menu = new PopupMenu(MainActivity.this,popupBtn);
                //加载菜单资源：利用MenuInflater将Menu资源加载到PopMenu.getMenu()所返回的Menu对象中
                //将R.menu.xx对应的菜单资源加载到弹出式菜单中
                menu.getMenuInflater().inflate(R.menu.popup,menu.getMenu());
                //为PopupMenu设置点击监听器
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.copy:
                                Toast.makeText(MainActivity.this,"复制",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.paste:
                                Toast.makeText(MainActivity.this,"粘贴",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
                //千万不要忘记这一步.显示PopupMenu
                menu.show();
            }
        });

    }
    ActionMode.Callback cb = new ActionMode.Callback() {
        //创建，在启动上下文操作模式（startActionMode(Callback)）时调用
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            Log.e("TAG","创建");
            getMenuInflater().inflate(R.menu.context,menu);
            return true;
        }

        //在创建方法后进行调用
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            Log.e("TAG","准备");
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.e("TAG","点击");
            switch (item.getItemId()){
                case R.id.delete:
                    Toast.makeText(MainActivity.this,"删除",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.opera1:
                    Toast.makeText(MainActivity.this,"操作1",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.opera2:
                    Toast.makeText(MainActivity.this,"操作2",Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }

        //上下文操作模式结束时被调用
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Log.e("TAG","结束");

        }
    };


/*
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                Toast.makeText(this,"删除",Toast.LENGTH_SHORT).show();
                break;
            case R.id.opera1:
                Toast.makeText(this,"操作1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.opera2:
                Toast.makeText(this,"操作2",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }*/

    //创建OptionMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单资源
        //通过xml资源来设计menu
       // getMenuInflater().inflate(R.menu.option,menu);
        //纯代码设计menu
        /*
        设置
        更多
           添加
           删除


         */
        //Menu
        //参数1：组id   参数2：菜单项id    参数3：序号 参数4：设置
        menu.add(1,1,1,"设置");
        //SubMenu
        SubMenu sub = menu.addSubMenu(1,2,2,"更多");
        sub.add(2,3,1,"添加");
        sub.add(2,4,2,"删除");
        //一定要记得返回true，否则中菜单不显示
        return true;
    }

    //OptionMenu菜单项选中方法
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
       /*  case  R.id.save:
        //
            Toast.makeText(this,"保存",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this,"设置",Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                finish();
          break;
          */
            case 1:
                Toast.makeText(this,"设置",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this,"更多",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this,"添加",Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this,"删除",Toast.LENGTH_SHORT).show();
                break;
            default:
                super.onOptionsItemSelected(item);




        }
        return true;
    }
}