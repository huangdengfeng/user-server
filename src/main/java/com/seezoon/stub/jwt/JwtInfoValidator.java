// Code generated by protoc-gen-validate. DO NOT EDIT.
// source: jwt.proto

package com.seezoon.stub.jwt;


/**
* Validates {@code JwtInfo} protobuf objects.
*/
@SuppressWarnings("all")
public class JwtInfoValidator implements io.envoyproxy.pgv.ValidatorImpl<com.seezoon.stub.jwt.JwtInfo>{
	public static io.envoyproxy.pgv.ValidatorImpl validatorFor(Class clazz) {
		if (clazz.equals(com.seezoon.stub.jwt.JwtInfo.class)) return new JwtInfoValidator();
		
		return null;
	}
		
	
		
	
		
	
		
	
		
	
		
	
		
	
		
	
	
	

	public void assertValid(com.seezoon.stub.jwt.JwtInfo proto, io.envoyproxy.pgv.ValidatorIndex index) throws io.envoyproxy.pgv.ValidationException {
	// no validation rules for Sub

	// no validation rules for Iss

	
			io.envoyproxy.pgv.RepeatedValidation.forEach(proto.getAudList(), item -> {
				// no validation rules for Aud

			});
	// no validation rules for Exp

	// no validation rules for Nbf

	// no validation rules for Iat

	// no validation rules for Jti

	// no validation rules for Attributes

	
	
	}

}

