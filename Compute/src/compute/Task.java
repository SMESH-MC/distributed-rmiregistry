/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compute;
import java.io.Serializable;
/**
 *
 * @author Arax
 */
public interface Task extends Serializable{
    String taskName();
    Object execute();
    int getClientID();
    void setClientID(int ID);
}
