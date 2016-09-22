package com.alisto.app.alisto;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.Date;
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

}
