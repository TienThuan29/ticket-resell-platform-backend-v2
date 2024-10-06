package swp391.adminservice.service.def;

import swp391.adminservice.dto.request.AuthenticationRequest;
import swp391.adminservice.dto.response.ApiResponse;
import swp391.adminservice.dto.response.AuthenticationResponse;
import swp391.adminservice.dto.response.StaffDTO;

public interface ISystemAuthService {

    ApiResponse<StaffDTO> getInfoByToken(String token);

    ApiResponse<AuthenticationResponse> authentication(AuthenticationRequest authRequest);

    ApiResponse<AuthenticationResponse> refreshToken(String refreshToken);

}
