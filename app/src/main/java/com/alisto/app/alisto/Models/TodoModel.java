package com.alisto.app.alisto.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;


import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Spoorthy on 9/20/2016.
 */
@Table(name="TodoItem")
public class TodoModel extends Model{

    @Column(name="item_description")
    public String description;
    @Column(name="priority")
    public String priority;
    @Column(name="due_date")
    public Date dueDate;

    public TodoModel(){
        super();
    }

    public TodoModel(String description, String priority, Date dueDate){
        super();
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public static ArrayList<TodoModel> getMockTodos(){
        Date d = new Date();
        ArrayList<TodoModel> todos = new ArrayList<TodoModel>();
        todos.add(new TodoModel("this is todo item 1", "HIGH", d));
        todos.add(new TodoModel("this is todo item 2", "MEDIUM", addDays(d,6)));
        todos.add(new TodoModel("this is todo item 2", "MEDIUM", addDays(d,1)));
        todos.add(new TodoModel("this is todo item 2", "LOW", addDays(d,3)));

        return todos;
    }


    public List<TodoModel> getAll()
    {
        return new Select().from(TodoModel.class).execute();
    }

    public TodoModel findRowdesc(String desc){
        return new Select().from(TodoModel.class).where("item_description = ?",desc).executeSingle();
    }
    public void deleteItemDesc(String desc){
        new Delete().from(TodoModel.class).where("item_description = ?", desc).execute();
    }


    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, days);

        return cal.getTime();
    }

}
