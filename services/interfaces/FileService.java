package file.server.services.interfaces;

import file.server.models.File;

import javax.validation.constraints.NotNull;

public interface FileService {

	File addFile(@NotNull File file);

	File getFileById(@NotNull File file);
}
