package gestionAplicacion.servicios;

public class Pedido {
	
	private String nombreproducto;
	private String tamañoProducto;
	private int cantidad;
	private double valor;
	private int codigo;
	
    public Pedido(){}
	
	public Pedido(int codigo, int cantidad) {
		this.codigo = codigo;
		this.cantidad = cantidad;
	}

	
	
	
	public String getNombreproducto() {
		return nombreproducto;
	}

	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTamañoProducto() {
		return tamañoProducto;
	}

	public void setTamañoProducto(String tamañoProducto) {
		this.tamañoProducto = tamañoProducto;
	}


	public static Pedido generarPedido(int codigoProducto, int cantidad) {
		Pedido pedido = new Pedido(codigoProducto,cantidad);
		for (int i=0;i<Inventario.getProductosEnInventario().size();i++) {
			if((codigoProducto == Inventario.getProductosEnInventario().get(i).getCodigoProducto()) && (cantidad <= Inventario.getProductosEnInventario().get(i).getCantidadDisponible())) {
				pedido.setNombreproducto(Inventario.getProductosEnInventario().get(i).getNombreProducto());
				pedido.setTamañoProducto(Inventario.getProductosEnInventario().get(i).getDescripcionTamañoProducto());
				pedido.setValor(Inventario.getProductosEnInventario().get(i).getPrecio()*cantidad);
				Inventario.getProductosEnInventario().get(i).setCantidadDisponible(Inventario.getProductosEnInventario().get(i).getCantidadDisponible() - cantidad);
				return pedido;
			}
		}
		return null;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
