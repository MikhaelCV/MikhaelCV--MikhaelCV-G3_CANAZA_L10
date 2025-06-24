
public class BTree<E extends Comparable<E>> {
	//construimos el arbol
	private BNode<E> root;
	private int orden;//orden

	private boolean up;//la mediana si sube
	private BNode<E> nDes;//nuevo nodo derecho despues de slitear

	public BTree(int orden) {
		this.orden = orden;//orden, maximo numero de hijos
		this.root = null;//esta vacio
	}
//vacio???
	public boolean isEmpty() {
		return this.root == null;
	}
//insertamos claves!!!!
	public void insert(E cl) {
		up = false;
		E mediana;
		BNode<E> pnew;//(primer nodo)se usará únicamente si hay que crear una nueva raíz
		mediana = push(this.root, cl);//push desciende por el árbol, inserta cl en la hoja adecuada, y va manejando splits de nodos llenos
			//Si en algún momento un nodo se divide y su mediana sube
		//creamos nuevo nodosi hace split
		if (up) {
			pnew = new BNode<E>(this.orden);//Se crea pnew, un nodo de orden orden, y se inserta en él la única clave mediana
			pnew.count = 1;//contador en 1
			pnew.keys.set(0, mediana);
			pnew.childs.set(0, this.root);
			pnew.childs.set(1, nDes);
			this.root = pnew;
		}
	}

	private E push(BNode<E> current, E cl) {//currete el nodo que quermos acomolar
		int pos[] = new int[1];
//un array de longitud uno, usado como contenedor para devolver por referencia el índice (hijo) correcto donde descender o la posición exacta de la clave si ya existe.		
		E mediana;
	//llegamos al final , hoja
		if (current == null) {
			up = true;
			nDes = null;//nDes = null porque aún no hay “medio nodo derecho” que adjuntar
			return cl;
		} else {
			boolean fl;
			fl = current.searchNode(cl, pos);//invocamos en buscador

			if (fl) {
				System.out.println("Item duplicado\n");
				up = false;//se anula el split
				return null;
			}
			mediana = push(current.childs.get(pos[0]), cl);
			//llamada recursiva al hijo pos[0], pasando la misma clave cl.
			//null, si no hubo promoción debajo,
			//o la clave que debe insertarse o promoverse en este nivel.
			if (up) {
				//Si está lleno , hay que hacer un split
				if (current.nodeFull(this.orden - 1))// reubicar un split
					mediana = dividedNode(current, mediana, pos[0]);
				else {//Si no está lleno,podemos simplemente insertar la clave en current
					up = false;
					putNode(current, mediana, nDes, pos[0]);
				}
			}
			return mediana;
		}
	}
//insertamos un nueva clave en el arbol derechoooooooo
	private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
		int i;
//hijos de i: childs i (izqi) y i+1 (derech)
		for (i = current.count - 1; i >= k; i--) {
			current.keys.set(i + 1, current.keys.get(i));//puntero al hijo derecho
			current.childs.set(i + 2, current.childs.get(i + 1));//el hijo que estaba en i+1 pasa a i+2
		}
		//aca ya deberiamos tener espacio en el inidice
		current.keys.set(k, cl);//insertamos nueva lcave
		current.childs.set(k + 1, rd); ////lo enlazamos
		current.count++;//y aumentmos el contador cochino
	}
//dividimos nosodos para los splitsssss
	
	private E dividedNode(BNode<E> current, E cl, int k) {
		//guardamos el nodo derecho en la posicion anterior
		BNode<E> rd = nDes;
		int i, posMdna;
		posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;

		nDes = new BNode<E>(this.orden);
		for (i = posMdna; i < this.orden - 1; i++) {
			nDes.keys.set(i - posMdna, current.keys.get(i));
			nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
		}

		nDes.count = (this.orden - 1) - posMdna;
		current.count = posMdna;

		if (k <= this.orden / 2)
			putNode(current, cl, rd, k);
		else
			putNode(nDes, cl, rd, k - posMdna);

		E median = current.keys.get(current.count - 1);
		nDes.childs.set(0, current.childs.get(current.count));
		current.count--;
		return median;
	}
//EJERCICIO 1 
	//BUSCAR
	public boolean search(E cl) {
        return searchRec(this.root, cl);
    }

    private boolean searchRec(BNode<E> current, E cl) {
        if (current == null) {//VACIO???
            return false;
        }
        int[] pos = new int[1];
	    //Se crea un array pos de tamaño 1 para capturar el índice donde searchNode localiza la clave o, en su defecto, dónde debería ir.
        if (current.searchNode(cl, pos)) {
            System.out.println(cl + " se encuentra en el nodo " + current + " en la posición " + pos[0]);
            return true;
        }
        return searchRec(current.childs.get(pos[0]), cl);
    }
    
    public E recover(E cl) {
        return recoverRec(root, cl);
    }

    private E recoverRec(BNode<E> node, E cl) {
        if (node == null) return null;
        int[] pos = new int[1];
        if (node.searchNode(cl, pos)) {
            return node.keys.get(pos[0]);
        }
        return recoverRec(node.childs.get(pos[0]), cl);
    }
    
    public void remove(E cl) {
        if (root == null) return;
        root = removeRec(root, cl);
        if (root != null && root.count == 0 && root.childs.get(0) != null) {
            root = root.childs.get(0);
        }
    }
    
    private BNode<E> removeRec(BNode<E> node, E cl) {
        int[] pos = new int[1];
        boolean found = node.searchNode(cl, pos);
        if (found) {
            if (node.childs.get(pos[0]) == null) {
                removeFromNode(node, pos[0]);
            } else {
                BNode<E> predNode = node.childs.get(pos[0]);
                if (predNode.count > (orden-1)/2) {
                    E pred = getMax(predNode);
                    node.keys.set(pos[0], pred);
                    node.childs.set(pos[0], removeRec(predNode, pred));
                } else {
                    BNode<E> succNode = node.childs.get(pos[0]+1);
                    if (succNode.count > (orden-1)/2) {
                        E succ = getMin(succNode);
                        node.keys.set(pos[0], succ);
                        node.childs.set(pos[0]+1, removeRec(succNode, succ));
                    } else {
                        mergeChildren(node, pos[0]);
                        node.childs.set(pos[0], removeRec(node.childs.get(pos[0]), cl));
                    }
                }
            }
        } else {
            BNode<E> child = node.childs.get(pos[0]);
            if (child != null) {
                node.childs.set(pos[0], removeRec(child, cl));
                if (node.childs.get(pos[0]).count < (orden-1)/2) {
                    fixUnderflow(node, pos[0]);
                }
            }
        }
        return node;
    }
    
    private void removeFromNode(BNode<E> node, int i) {
        for (int j = i; j < node.count-1; j++) {
            node.keys.set(j, node.keys.get(j+1));
        }
        node.keys.set(node.count-1, null);
        node.count--;
    }
    
    private void fixUnderflow(BNode<E> parent, int idx) {
        BNode<E> child = parent.childs.get(idx);
        int minKeys = (orden - 1) / 2;
        // Intentar rotación con hermano izquierdo
        if (idx > 0) {
            BNode<E> left = parent.childs.get(idx - 1);
            if (left.count > minKeys) {
                // Rotación derecha
                // Mover clave del padre al inicio de child
                child.insertKey(parent.keys.get(idx - 1), 0);
                // Mover último hijo de left al primer hijo de child
                child.childs.set(1, child.childs.get(0));
                child.childs.set(0, left.childs.get(left.count));
                // Actualizar padre
                parent.keys.set(idx - 1, left.keys.get(left.count - 1));
                left.removeKey(left.count - 1);
                return;
            }
        }
        // Intentar rotación con hermano derecho
        if (idx < parent.count) {
            BNode<E> right = parent.childs.get(idx + 1);
            if (right.count > minKeys) {
                // Rotación izquierda
                child.insertKey(parent.keys.get(idx), child.count);
                child.childs.set(child.count + 1, right.childs.get(0));
                parent.keys.set(idx, right.keys.get(0));
                right.removeKey(0);
                return;
            }
        }
        // No se pudo redistribuir: fusionar
        if (idx > 0) mergeChildren(parent, idx - 1);
        else mergeChildren(parent, idx);
    }
    
    private void mergeChildren(BNode<E> parent, int idx) {
        BNode<E> left  = parent.childs.get(idx);
        BNode<E> right = parent.childs.get(idx + 1);

        // 1) Llevar la clave del padre a left
        left.keys.set(left.count, parent.keys.get(idx));
        left.count++;

        // 2) Copiar claves e hijos de right a left
        for (int j = 0; j < right.count; j++) {
            left.keys.set(left.count + j, right.keys.get(j));
        }
        for (int j = 0; j <= right.count; j++) {
            left.childs.set(left.count + j, right.childs.get(j));
        }
        left.count += right.count;

        // 3) Eliminar la clave y puntero a right del padre
        for (int j = idx; j < parent.count - 1; j++) {
            parent.keys.set(j, parent.keys.get(j + 1));
            parent.childs.set(j + 1, parent.childs.get(j + 2));
        }
        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
        parent.count--;
    }
    
    
    
    private E getMax(BNode<E> node) {
        while (node.childs.get(node.count) != null) {
            node = node.childs.get(node.count);
        }
        return node.keys.get(node.count - 1);
    }
    
    private E getMin(BNode<E> node) {
        while (node.childs.get(0) != null) {
            node = node.childs.get(0);
        }
        return node.keys.get(0);
    }
    
    public String toString() {
        if (isEmpty()) {
            return "BTree is empty...";
        }
        String str = "";
        // Cabecera de tabla
        str = str + String.format("%-7s %-15s %-9s %s%n", "Nodo", "ClavesNodo", "IdPadre", "IdHijos");
        // Cuerpo: preorden con info de cada nodo
        str = str + writeTree(root, null);
        return str;
    }
    
    private String writeTree(BNode<E> node, BNode<E> parent) {
        if (node == null) return "";
        // Fila de este nodo
        String fila = "";
        int idNodo = node.getIdNode();
        // Claves
        String claves = "(";
        for (int i = 0; i < node.count; i++) {
            claves = claves + node.keys.get(i);
            if (i < node.count - 1) claves = claves + ",";
        }
        claves = claves + ")";
        // Padre
        String idPadre = (parent == null) ? "--" : String.valueOf(parent.getIdNode());
        // Hijos
        String idHijos = "";
        for (int i = 0; i <= node.count; i++) {
            BNode<E> ch = node.childs.get(i);
            if (ch != null) {
                if (!idHijos.isEmpty()) idHijos = idHijos + ",";
                idHijos = idHijos + ch.getIdNode();
            }
        }
        if (idHijos.isEmpty()) idHijos = "--";
        else idHijos = "[" + idHijos + "]";
        // Concatenar fila completa
        fila = fila + String.format("%-7d %-15s %-9s %s%n", idNodo, claves, idPadre, idHijos);
        // Recorrer hijos
        for (int i = 0; i <= node.count; i++) {
            fila = fila + writeTree(node.childs.get(i), node);
        }
        return fila;
    }
}
