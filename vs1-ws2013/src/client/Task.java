/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface fuer Remote-Tasks
 * @author Arax
 */
public interface Task<T> extends Remote{
    T execute() throws RemoteException;
}
