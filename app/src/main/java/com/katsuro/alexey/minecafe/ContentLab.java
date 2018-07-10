package com.katsuro.alexey.minecafe;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alexey on 7/10/18.
 */

public class ContentLab {

    private Category mStart;



    private static ContentLab sContentLab;
    private Context mContext;
    private Resources mRes;


    public static ContentLab get(Context context){
        if(sContentLab == null){
            sContentLab = new ContentLab(context);

        }
        return sContentLab;
    }

    private ContentLab(Context context){
        mContext = context.getApplicationContext();
        mRes = mContext.getResources();

    }

    public Category getStartCategory() {
        List<Content> categoryList = new ArrayList<>();
        List<Content> burgers = new ArrayList<>();
        burgers.add(new Detail("Burger1", "BurgerDescription1", R.drawable.burger, R.drawable.burger));
        burgers.add(new Detail("Burger2", "BurgerDescription2", R.drawable.burger, R.drawable.sandwich));
        burgers.add(new Detail("Burger3", "BurgerDescription3", R.drawable.burger, R.drawable.burger));


        mStart = new Category("Start", R.mipmap.ic_launcher,categoryList);
        Category category = new Category(mRes.getString(R.string.burgers), R.drawable.burger, burgers);
        Category category1 = new Category(mRes.getString(R.string.sandwiches), R.drawable.sandwich, null);
        Category category2 = new Category(mRes.getString(R.string.pizza), R.drawable.pizza, null);
        Category category3 = new Category(mRes.getString(R.string.salads), R.drawable.salad, null);
        Category category4 = new Category(mRes.getString(R.string.beverages), R.drawable.beverage, null);
        Category category5 = new Category(mRes.getString(R.string.dessert), R.drawable.dessert, null);

        categoryList.add(category);
        categoryList.add(category1);
        categoryList.add(category2);
        categoryList.add(category3);
        categoryList.add(category4);
        categoryList.add(category5);

        return mStart;
    }

    public Content getContent(String title){
        return getContent(title,mStart);
    }
    public Content getContent(String title, Category category){
        Content result = null;
        for (Content content : category.getContents()){
            if(content.getTitle().equals(title)){
                return content;
            } else if(content instanceof Category){
               result = getContent(title, (Category) content);
            }
            if(result != null){
                return result;
            }
        }
        return result;
    }
}
