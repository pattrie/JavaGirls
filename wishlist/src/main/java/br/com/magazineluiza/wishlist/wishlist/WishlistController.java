package br.com.magazineluiza.wishlist.wishlist;

import br.com.magazineluiza.wishlist.client.ClientDTO;
import br.com.magazineluiza.wishlist.common.ApiResponse;
import br.com.magazineluiza.wishlist.product.Product;
import br.com.magazineluiza.wishlist.product.ProductDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@ApiOperation(value ="/wishlist", tags = "Wishlist Controller" )
@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @ApiOperation(value= "Returns wishlist for a given client id")
    @GetMapping("{clientId}")
    public List<Product> getProdutosByClientId(@PathVariable(value = "clientId") Integer clientId)
        throws NotFoundException {
        return wishlistService.getProductsBy(clientId);
    }

    @ApiOperation(value= "Returns a product searched for by name")
    @GetMapping("/{clientId}/product/{name}")
    public ResponseEntity<List<ProductDTO>> getProdutosByName(@PathVariable(value = "clientId") Integer clientId, @PathVariable(value = "name") String productName) {
       return wishlistService.getProductsByName(productName, clientId);
    }

    @ApiOperation(value= "Add a product to the client's wishlist")
    @PostMapping("/{clientId}/{productId}")
    public ResponseEntity<ApiResponse> addProduct(@PathVariable("clientId") Integer clientId, @PathVariable("productId") Integer productId){
            return wishlistService.addProduct(clientId, productId);
    }

    @ApiOperation(value= "Deletes a product in the customer's wishlist by id")
    @DeleteMapping("/{clientId}/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("clientId") Integer clientId, @PathVariable("productId") Integer productId){
        return wishlistService.deleteProduct(clientId, productId);
    }

}
