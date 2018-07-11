package com.katsuro.alexey.minecafe;

import android.content.Context;
import android.content.res.Resources;

import com.katsuro.alexey.minecafe.model.Category;
import com.katsuro.alexey.minecafe.model.Content;
import com.katsuro.alexey.minecafe.model.Detail;

import java.util.ArrayList;
import java.util.List;

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


//        List<Content> beverages = new ArrayList<>();
//



        mStart = new Category(mRes.getString(R.string.app_name), R.mipmap.ic_launcher,categoryList);

        categoryList.add(getBurgersCategory());
        categoryList.add(getSandwichesCategory());
        categoryList.add(getPizzaCategory());
        categoryList.add(getSaladsCategory());
        categoryList.add(getBeverageCategory());
        categoryList.add(getDessertCategory());

        return mStart;
    }

    public Category getBurgersCategory(){
        List<Content> details = new ArrayList<>();
        String title = mRes.getString(R.string.burgers);
        String description = title + " description";

        details.add(new Detail(title+"1",description+"1",R.drawable.cafe,R.drawable.burger));
        details.add(new Detail(title+"2",description+"2",R.drawable.cafe,R.drawable.burger));
        details.add(new Detail(title+"3",description+"3",R.drawable.cafe,R.drawable.sandwich));

        return new Category(mRes.getString(R.string.burgers), R.drawable.burger, details);
    }

    public Category getSandwichesCategory(){

        List<Content> details = new ArrayList<>();
        String title = mRes.getString(R.string.sandwiches);
        String description = title + " description";
        details.add(new Detail(title+"1",description+"1",R.drawable.cafe,R.drawable.sandwich));
        details.add(new Detail(title+"2",description+"2",R.drawable.cafe,R.drawable.burger));
        details.add(new Detail(title+"3",description+"3",R.drawable.cafe,R.drawable.sandwich));

        return new Category(mRes.getString(R.string.sandwiches), R.drawable.sandwich, details);
    }

    public Category getPizzaCategory(){
        List<Content> details = new ArrayList<>();
        String title = mRes.getString(R.string.pizza);
        String description = title + " description";
        details.add(new Detail(title+"1",description+"1",R.drawable.cafe,R.drawable.pizza));
        details.add(new Detail(title+"2",description+"2",R.drawable.cafe,R.drawable.salad));
        details.add(new Detail(title+"3",description+"3",R.drawable.cafe,R.drawable.pizza));
        return new Category(mRes.getString(R.string.pizza), R.drawable.pizza, details);
    }

    public Category getSaladsCategory(){
        List<Content> details = new ArrayList<>();
        String title = mRes.getString(R.string.salads);
        String description = title + " description";
        details.add(new Detail(title+"1",description+"1",R.drawable.cafe,R.drawable.salad));
        details.add(new Detail(title+"2",description+"2",R.drawable.cafe,R.drawable.salad));
        details.add(new Detail(title+"3",description+"3",R.drawable.cafe,R.drawable.pizza));
        return new Category(mRes.getString(R.string.salads), R.drawable.salad, details);
    }

    public Category getBeverageCategory(){
        List<Content> categories = new ArrayList<>();
        String title = mRes.getString(R.string.beverages);
        String description = title + " description";

        List<Content> detailsHot = new ArrayList<>();
        detailsHot.add(new Detail(mRes.getString(R.string.hot)+" "+ title + "1",
                mRes.getString(R.string.hot)+" "+ description+ "1",
                R.drawable.cafe,R.drawable.hot_drink));
        detailsHot.add(new Detail(mRes.getString(R.string.hot)+" "+ title+ "2",
                mRes.getString(R.string.hot)+" "+ description+ "2",
                R.drawable.cafe,R.drawable.beverage));

        List<Content> detailsFresh = new ArrayList<>();
        detailsFresh.add(new Detail(mRes.getString(R.string.fresh)+" "+ title+ "1",
                mRes.getString(R.string.fresh)+" "+ description+ "1",
                R.drawable.cafe,R.drawable.fresh_drink));
        detailsFresh.add(new Detail(mRes.getString(R.string.fresh)+" "+ title+ "2",
                mRes.getString(R.string.fresh)+" "+ description+ "2",
                R.drawable.cafe,R.drawable.fresh_drink));
        detailsFresh.add(new Detail(mRes.getString(R.string.fresh)+" "+ title+ "3",
                mRes.getString(R.string.fresh)+" "+ description+ "3",
                R.drawable.cafe,R.drawable.fresh_drink));

        List<Content> detailsAcloholic = new ArrayList<>();
        detailsAcloholic.add(new Detail(mRes.getString(R.string.alcoholic)+" "+ title,
                mRes.getString(R.string.alcoholic)+" "+ description,
                R.drawable.cafe,R.drawable.alcoholic_drink));

        categories.add(new Category(mRes.getString(R.string.hot),R.drawable.hot_drink,detailsHot));
        categories.add(new Category(mRes.getString(R.string.fresh),R.drawable.fresh_drink,detailsFresh));
        categories.add(new Category(mRes.getString(R.string.alcoholic),R.drawable.alcoholic_drink,detailsAcloholic));
        return new Category(mRes.getString(R.string.beverages), R.drawable.beverage, categories);
    }

    public Category getDessertCategory(){
        List<Content> details = new ArrayList<>();
        String title = mRes.getString(R.string.dessert);
        String description = title + "description";
        details.add(new Detail(title+"1",description+"1",R.drawable.cafe,R.drawable.dessert));
        details.add(new Detail(title+"2",description+"2",R.drawable.cafe,R.drawable.dessert));
        details.add(new Detail(title+"3",description+"3",R.drawable.cafe,R.drawable.beverage));
        return new Category(mRes.getString(R.string.dessert), R.drawable.dessert, details);
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
