username test: abc@gmail.com
password: Loocreative01@


**Functions which I implemented**
  1. Join
  2. Login
  3. User-list
  4. User-detail
  5. Update-user
  6. Delete-user



**Interfaces which I used** 
  1. Interface CrudRepository: The central interface in Spring Data repository abstraction is Repository (probably not that much of a surprise). It takes the the domain class to manage as well as the id type of the domain class as type arguments. This interface acts primarily as a marker interface to capture the types to work with and to help you to discover interfaces that extend this one. The CrudRepository provides sophisticated CRUD functionality for the entity class that is being managed.
  2. Interface UserDetailsService: Locates the user based on the username. In the actual implementation, the search may possibly be case sensitive, or case insensitive depending on how the implementation instance is configured. In this case, the UserDetails object that comes back may have a username that is of a different case than what was actually requested.
  3. Interface RedirectAttributes: A RedirectAttributes model is empty when the method is called and is never used unless the method returns a redirect view name or a RedirectView. After the redirect, flash attributes are automatically added to the model of the controller that serves the target URL.
  4. Interface Model: Java-5-specific interface that defines a holder for model attributes. Primarily designed for adding attributes to the model. Allows for accessing the overall model as a java.util.Map.
  5. Interface BindingResult: General interface that represents binding results. Extends the interface for error registration capabilities, allowing for a Validator to be applied, and adds binding-specific analysis and model building. Serves as result holder for a DataBinder, obtained via the DataBinder.getBindingResult() method. BindingResult implementations can also be used directly, for example to invoke a Validator on it (e.g. as part of a unit test).
  6. Interface MultipartFile: A representation of an uploaded file received in a multipart request. The file contents are either stored in memory or temporarily on disk. In either case, the user is responsible for copying file contents to a session-level or persistent store as and if desired. The temporary storage will be cleared at the end of request processing.
  7. Interface WebMvcConfigurer: Defines callback methods to customize the Java-based configuration for Spring MVC enabled via @EnableWebMvc. @EnableWebMvc-annotated configuration classes may implement this interface to be called back and given a chance to customize the default configuration.
  8. Interface WebSecurityConfigurer: Allows customization to the WebSecurity. In most instances users will use EnableWebSecurity and either create a Configuration that extends WebSecurityConfigurerAdapter or expose a SecurityFilterChain bean. Both will automatically be applied to the WebSecurity by the EnableWebSecurity annotation.
