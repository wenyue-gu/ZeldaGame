package ooga.view.engine.graphics.render;

import static org.lwjgl.opengl.GL11C.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11C.GL_SRC_ALPHA;

import ooga.view.engine.graphics.Shader;
import ooga.view.engine.io.Window;
import ooga.view.engine.maths.Matrix4f;
import ooga.view.engine.objects.Camera;
import ooga.view.engine.objects.GameObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public class Renderer2D {
	private Shader shader;
	//private Window window;

	public Renderer2D(Shader shader) {
		this.shader = shader;
		//this.window = window;
	}
	
	public void renderMesh(GameObject object) {
		GL30.glBindVertexArray(object.getMesh().getVAO());
		GL30.glEnableVertexAttribArray(0);
		//GL30.glEnableVertexAttribArray(1);
		GL30.glEnableVertexAttribArray(2);
		GL30.glEnable(GL11.GL_BLEND);
		GL30.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		//GL30.glBlendFunc(GL11.GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, object.getMesh().getIBO());
		//GL13.glActiveTexture(GL13.GL_TEXTURE0);
		//GL13.glBindTexture(GL11.GL_TEXTURE_2D, object.getMesh().getMaterial().getTextureID());
		object.getMesh().getMaterial().bind();
		shader.bind();
		shader.setUniform("model", Matrix4f.transform(object.getPosition(), object.getRotation(), object.getScale()));
		//shader.setUniform("view", Matrix4f.view(camera.getPosition(), camera.getRotation()));
		//shader.setUniform("projection", window.getProjectionMatrix());
		//GL11.glClearColor(0.7f, 0.7f, 0.7f, 1.0f);
		GL11.glDrawElements(GL11.GL_TRIANGLES, object.getMesh().getIndices().length, GL11.GL_UNSIGNED_INT, 0);
		shader.unbind();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL30.glDisableVertexAttribArray(0);
		GL30.glDisableVertexAttribArray(1);
		GL30.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}
}