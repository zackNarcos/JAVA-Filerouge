/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package dao;

import java.util.List;

/**
 *
 * @author HP
 */
public interface IDao<T> {
/*
Fonction MAJ
return int
    insert=>dernier in inserer
    update=>nbre de ligne modifier

*/
public int insert(T ogj);
public int update(T ogj);
public int delete(int id);



/*
Fonction Interrogation
    return List findAll()
    return un objetvfindById()
*/
public List<T> findAll();
public T findById(int id);
//public T findByNci(String nci);
    

}
